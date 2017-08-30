package com.mobile.anime.animerecommendations.view

import android.app.Activity
import android.content.Context
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
import javax.inject.Inject

/**
 * Created by rww306 on 2017-08-28.
 */
@Layout(R.layout.card_view_layout)
class AnimeCard (context : Activity, swipeView : SwipePlaceHolderView, id : Int){

    @Inject
    lateinit var bus : Bus

    @View(R.id.title)
    private var title : TextView? = null

    private var context : Activity? = null
    private var swipeView : SwipePlaceHolderView? = null
    private var id = 0

    init {
        this.context = context
        this.swipeView = swipeView
        this.id = id
        (context.application as HikariApplication).component!!.inject(this)
        bus.register(this)
    }

    @Resolve
    private fun onResolved() {
        //set up view here
        bus.post(JikanAnimeRequest(id))
    }

    @SwipeOut
    private fun onSwipeOut() {
        swipeView!!.addView(this)
    }

    @SwipeIn
    private fun onSwipeIn() {
        swipeView!!.addView(this)
    }

    @Subscribe
    public fun onJikanAnimeResponse(response: JikanAnimeResponse) {
        Toast.makeText(context, "RESPONE!!", Toast.LENGTH_SHORT).show()
    }
}