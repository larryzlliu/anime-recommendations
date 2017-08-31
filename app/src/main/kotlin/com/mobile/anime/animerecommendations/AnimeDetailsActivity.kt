package com.mobile.anime.animerecommendations

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.Tags
import com.mobile.anime.animerecommendations.view.AnimeDetailsAdapter
import kotlinx.android.synthetic.main.anime_details_activity_layout.*

/**
 * Created by rww306 on 2017-08-31.
 */
class AnimeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.anime_details_activity_layout)
        val anime : JikanAnimeResponse = intent.extras.getSerializable(Tags.ANIME) as JikanAnimeResponse
        val animeList = ArrayList<JikanAnimeResponse>()
        animeList.add(anime)
        view_pager.adapter = AnimeDetailsAdapter(animeList, supportFragmentManager)
    }
}