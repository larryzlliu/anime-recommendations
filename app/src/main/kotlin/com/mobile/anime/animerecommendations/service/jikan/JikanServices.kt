package com.mobile.anime.animerecommendations.service.jikan

/**
 * Created by rww306 on 2017-08-29.
 */
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by rww306 on 2017-06-13.
 */

interface JikanServices {
    @GET("/api/anime/{id}")
    fun getAnimeById(@Path("id") id : Int): Call<JikanAnimeResponse>
}