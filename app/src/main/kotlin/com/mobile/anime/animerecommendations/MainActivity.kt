package com.mobile.anime.animerecommendations

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import com.mobile.anime.animerecommendations.service.request.JikanAnimeRequest
import com.mobile.anime.animerecommendations.service.request.MALGetUserIdRequest
import com.mobile.anime.animerecommendations.service.response.JikanAnimeResponse
import com.mobile.anime.animerecommendations.util.Tags
import com.mobile.anime.animerecommendations.util.clearSharedPref
import com.mobile.anime.animerecommendations.view.AnimeCard
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var bus : Bus

    private var testList : ArrayList<Int>? = null

    private var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        (application as HikariApplication).component!!.inject(this)
        bus.register(this)

        bus.post(MALGetUserIdRequest())

        val swipeViewBuilder : SwipeViewBuilder<SwipePlaceHolderView> = swipeView.getBuilder()
        swipeViewBuilder.setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor().setPaddingTop(0)
                        .setRelativeScale(0.01f))

        button = findViewById(R.id.button_favourites)
        button!!.setOnClickListener {
            this.startFavouriteActivity()
        }

        testList = ArrayList()
        testList!!.add(1)
        testList!!.add(9253)
        testList!!.add(32281)
        testList!!.add(1575)

        testList!!.forEach { i ->
            bus.post(JikanAnimeRequest(i))
        }

        cancel_button.setOnClickListener{
            swipeView.doSwipe(false)
        }

        heart_button.setOnClickListener {
            swipeView.doSwipe(true)
        }
    }

    @Subscribe
    fun onAnimeResponse(response: JikanAnimeResponse) {
        swipeView.addView(AnimeCard(this, swipeView, response))
    }

    fun startAnimeActivity(anime : JikanAnimeResponse) {
        val intent = Intent(this, AnimeDetailsActivity::class.java)
        intent.putExtra(Tags.ANIME, anime)
        startActivity(intent)
    }

    fun startFavouriteActivity() {
        val intent = Intent(this, FavouriteActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        //only for testing purposes, clear sharedpref
        clearSharedPref(this)
    }

}
