package com.mobile.anime.animerecommendations.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobile.anime.animerecommendations.AnimeDetailsFragment
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.Tags

/**
 * Created by rww306 on 2017-08-31.
 */
class AnimeDetailsAdapter(animeList: ArrayList<JikanAnimeResponse>, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    private var animeList : ArrayList<JikanAnimeResponse>? = null

    init {
        this.animeList = animeList
    }

    override fun getCount(): Int {
        return animeList!!.size
    }

    override fun getItem(position: Int): Fragment {
        val frag : AnimeDetailsFragment = AnimeDetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable(Tags.ANIME, animeList!![position])
        frag.arguments = bundle
        return frag
    }
}
