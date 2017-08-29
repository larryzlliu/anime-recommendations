package com.mobile.anime.animerecommendations

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import com.mobile.anime.animerecommendations.view.AnimeCard
import kotlinx.android.synthetic.main.card_view_layout.view.*
import kotlinx.android.synthetic.main.content_main.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val swipeViewBuilder : SwipeViewBuilder<SwipePlaceHolderView> = swipeView.getBuilder()
        swipeViewBuilder.setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor().setPaddingTop(0)
                        .setRelativeScale(0.01f))

        for (i in 0..2) {
            swipeView.addView(AnimeCard(this, swipeView))
        }
    }

}
