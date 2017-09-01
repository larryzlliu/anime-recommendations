package com.mobile.anime.animerecommendations.view

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.mobile.anime.animerecommendations.MainActivity
import com.mobile.anime.animerecommendations.R
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.addAnime
import com.squareup.picasso.Picasso

/**
 * Created by rww306 on 2017-08-28.
 */
@Layout(R.layout.card_view_layout)
class AnimeCard (context : Activity, swipeView : SwipePlaceHolderView, anime : JikanAnimeResponse){

    @View(R.id.title)
    private var title : TextView? = null
    @View(R.id.image)
    private var image : ImageView? = null
    @View(R.id.description)
    private var description : TextView? = null

    private var context : Activity? = null
    private var swipeView : SwipePlaceHolderView? = null
    private var anime : JikanAnimeResponse? = null

    init {
        this.context = context
        this.swipeView = swipeView
        this.anime = anime
    }

    @Resolve
    private fun onResolved() {
        title!!.text = anime!!.title
        Picasso.with(context).load(anime!!.image).into(image)
        description!!.text = anime!!.synopsis

        image!!.setOnClickListener {
            (context as MainActivity).startAnimeActivity(this.anime!!)
        }
    }

    @SwipeOut
    private fun onSwipeOut() {
        swipeView!!.addView(this)
    }

    @SwipeIn
    private fun onSwipeIn() {
        swipeView!!.addView(this)
        addAnime(anime!!.id, context!!)
    }
}