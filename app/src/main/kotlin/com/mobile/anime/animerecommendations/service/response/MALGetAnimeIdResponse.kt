package com.mobile.anime.animerecommendations.service.response

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Root

/**
 * Created by larryliu on 2017-09-19.
 */
@Root(strict = false)
class MALGetAnimeIdResponse {

    var animes : List<AnimeInfoResponse>? = null

    inner class AnimeInfoResponse {

        @SerializedName("series_animedb_id")
        var id : String? = null
    }
}