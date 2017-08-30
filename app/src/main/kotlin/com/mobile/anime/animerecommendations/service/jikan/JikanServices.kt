package com.mobile.anime.animerecommendations.service.jikan

/**
 * Created by rww306 on 2017-08-29.
 */
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by rww306 on 2017-06-13.
 */

interface FLCSService {
    @GET("/anime/{id}")
    fun getAnimeById(@Path("id") id : Int): Call<JikanAnimeEntry>
}