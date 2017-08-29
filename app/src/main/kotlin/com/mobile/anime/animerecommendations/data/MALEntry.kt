package com.mobile.anime.animerecommendations.data

/**
 * Created by fanxing on 8/24/2017.
 */
class MALEntry (name : String){
    private var name : String

    init {
        this.name = name
    }

    public fun getName() : String {
        return name
    }
}