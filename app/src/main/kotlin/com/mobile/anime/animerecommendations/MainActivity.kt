package com.mobile.anime.animerecommendations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import com.mobile.anime.animerecommendations.view.AnimeCard


class MainActivity : AppCompatActivity() {

    private var testList : ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val swipeViewBuilder : SwipeViewBuilder<SwipePlaceHolderView> = swipeView.getBuilder()
        swipeViewBuilder.setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor().setPaddingTop(0)
                        .setRelativeScale(0.01f))

        testList = ArrayList()
        testList!!.add(1)
        testList!!.add(9253)
        testList!!.add(32281)
        testList!!.add(1575)

        testList!!.forEach { i ->
            swipeView.addView(AnimeCard(this, swipeView, i))
        }

        cancel_button.setOnClickListener{
            swipeView.doSwipe(false)
        }

        heart_button.setOnClickListener {
            swipeView.doSwipe(true)
        }
    }

}
