package com.mobile.anime.animerecommendations

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.Tags
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view_layout.view.*

/**
 * Created by rww306 on 2017-08-31.
 */

class AnimeDetailsFragment : android.support.v4.app.Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.card_details_layout, container, false)
        val anime = arguments.getSerializable(Tags.ANIME) as JikanAnimeResponse
        Picasso.with(context).load(anime.image).into(view.image)
        view.title.text = anime.title
        view.description.text = anime.synopsis
        view.image.setOnClickListener { activity.finish() }
        return view
    }
}