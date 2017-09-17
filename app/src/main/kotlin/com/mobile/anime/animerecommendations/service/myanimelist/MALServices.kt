package com.mobile.anime.animerecommendations.service.myanimelist

/**
 * Created by larryliu on 2017-09-10.
 */
import retrofit2.Call
import retrofit2.http.GET

interface MALServices {
    @GET("malappinfo.php?u=LarryFanCaliBust&status=all&type=anime")
    fun getUserAnimeList(): Call<String>
}