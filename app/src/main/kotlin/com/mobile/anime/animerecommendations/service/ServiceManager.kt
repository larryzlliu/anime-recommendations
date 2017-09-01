package com.mobile.anime.animerecommendations.service

import android.util.Log
import com.mobile.anime.animerecommendations.service.jikan.JikanRetrofitBuilder
import com.mobile.anime.animerecommendations.service.jikan.JikanServices
import com.mobile.anime.animerecommendations.service.request.JikanAnimeRequest
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.Cache
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response


/**
 * Created by rww306 on 2017-08-29.
 */
@Singleton
class ServiceManager public @Inject constructor(bus : Bus){

    private var bus : Bus? = null
    private var jikanService : JikanServices? = null

    init {
        this.bus = bus
        bus.register(this)
        val retrofit = JikanRetrofitBuilder.retrofit
        jikanService = retrofit.create(JikanServices::class.java)
    }

    @Subscribe
    public fun onJikanAnimeRequest(request: JikanAnimeRequest) {
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
}