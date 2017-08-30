package com.mobile.anime.animerecommendations.view

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.mobile.anime.animerecommendations.HikariApplication
import com.mobile.anime.animerecommendations.R
import com.mobile.anime.animerecommendations.service.request.JikanAnimeRequest
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by rww306 on 2017-08-28.
 */
@Layout(R.layout.card_view_layout)
class AnimeCard (context : Activity, swipeView : SwipePlaceHolderView, anime: JikanAnimeResponse){

    @View(R.id.title)
    private var title : TextView? = null
    @View(R.id.image)
    private var image : ImageView? = null

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
        title!!.setText(anime!!.title)
        Picasso.with(context).load(anime!!.image).into(image)
    }

    @SwipeOut
    private fun onSwipeOut() {
        swipeView!!.addView(this)
    }

    @SwipeIn
    private fun onSwipeIn() {
        swipeView!!.addView(this)
    }
}