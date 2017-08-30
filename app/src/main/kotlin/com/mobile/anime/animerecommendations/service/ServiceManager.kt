package com.mobile.anime.animerecommendations.service

import android.util.Log
import com.squareup.otto.Bus
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rww306 on 2017-08-29.
 */
@Singleton
class ServiceManager public @Inject constructor(bus : Bus){

    private var bus : Bus? = null

    init {
        this.bus = bus
        bus.register(this)
    }
}