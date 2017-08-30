package com.mobile.anime.animerecommendations.service.response

/**
 * Created by rww306 on 2017-08-29.
 */

class JikanAnimeResponse {
    private var title : String? = null
    private var synopsis : String? = null

    fun getTitle(): String {
        return title!!
    }
}