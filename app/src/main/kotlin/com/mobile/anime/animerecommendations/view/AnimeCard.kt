package com.mobile.anime.animerecommendations.view

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.mobile.anime.animerecommendations.R

/**
 * Created by rww306 on 2017-08-28.
 */
@Layout(R.layout.card_view_layout)
class AnimeCard (context : Context, swipeView : SwipePlaceHolderView){
    @View(R.id.title)
    var title : TextView? = null

    private var context : Context? = null
    private var swipeView : SwipePlaceHolderView? = null

    init {
        this.context = context
        this.swipeView = swipeView
    }

    @Resolve
    private fun onResolved() {
        //set up view here
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