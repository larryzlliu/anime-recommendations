package com.mobile.anime.animerecommendations.service.jikan

/**
 * Created by rww306 on 2017-08-29.
 */
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanServices {
    @GET("anime/{id}")
    fun getAnimeById(@Path("id") id : Int): Call<JikanAnimeResponse>
}