package com.mobile.anime.animerecommendations.service

import com.mobile.anime.animerecommendations.service.jikan.JikanServices
import com.mobile.anime.animerecommendations.service.request.JikanAnimeRequest
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.service.RetrofitBuilder
import com.mobile.anime.animerecommendations.service.myanimelist.MALServices
import com.mobile.anime.animerecommendations.service.request.MALGetUserIdRequest
import com.mobile.anime.animerecommendations.util.Cache
import com.mobile.anime.animerecommendations.util.MALXmlParse
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


/**
 * Created by rww306 on 2017-08-29.
 */
@Singleton
class ServiceManager public @Inject constructor(bus : Bus){

    private var bus : Bus? = null
    private var jikanService : JikanServices? = null
    private var MALService : MALServices? = null
    private var xmlParser : MALXmlParse

    init {
        this.bus = bus
        bus.register(this)
        xmlParser = MALXmlParse()

        val jikanRetrofitBuilder = RetrofitBuilder("http://jikan.me/api/")
        val jikanRetrofit = jikanRetrofitBuilder.getRetrofit()
        jikanService = jikanRetrofit.create(JikanServices::class.java)

        val MALRetrofitBuilder = RetrofitBuilder("https://myanimelist.net/")
        val MALRetrofit = MALRetrofitBuilder.getRetrofit()
        MALService = MALRetrofit.create(MALServices::class.java)
    }

    @Subscribe
    fun onJikanAnimeRequest(request: JikanAnimeRequest) {
        val cachedResponse = Cache.get(request.id)
        if (cachedResponse != null) {
            bus!!.post(cachedResponse)
            return
        }

        val call : Call<JikanAnimeResponse> = jikanService!!.getAnimeById(request.id)
        call.enqueue(object : Callback<JikanAnimeResponse> {

            override fun onResponse(call : Call<JikanAnimeResponse>, response: Response<JikanAnimeResponse>) {
                if (response.body() != null) {
                    val body = response.body()
                    body.id = request.id
                    Cache.put(request.id, body)
                    bus!!.post(response.body())
                }
            }

            override fun onFailure(call : Call<JikanAnimeResponse>, t : Throwable) {

            }

        })
    }

    @Subscribe
    fun onMALGetIdRequest(request: MALGetUserIdRequest) {
        val call : Call<String> = MALService!!.getUserAnimeList()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                System.out.println("--------------Before null check\n")
                if (response!!.body() != null) {
                    System.out.println("--------------After null check\n")
                    val stream = ByteArrayInputStream(response.body().toByteArray(StandardCharsets.UTF_8))
                    val animes = xmlParser.parse(stream)
                    bus!!.post(animes)
                    System.out.println("--------------\n")
                    for (anime in animes) {
                        System.out.println(anime.id)
                    }
                }
            }

            override fun onFailure(call : Call<String>, t : Throwable) {
                System.out.println("--------------Before null check\n")
            }
        })
    }
}