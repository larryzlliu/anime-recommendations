package com.mobile.anime.animerecommendations.service.jikan

/**
 * Created by rww306 on 2017-08-29.
 */

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rww306 on 2017-06-13.
 */

object JikanRetrofitBuilder {
    val JIKAN_API_URL : String = "https://jikan.me/api/v1.1/"

    val retrofit: Retrofit
        get() {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val url = original.url().newBuilder()
                        .build()

                val requestBuilder = original.newBuilder()
                        .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            val client = httpClient.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(JIKAN_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return retrofit
        }
}