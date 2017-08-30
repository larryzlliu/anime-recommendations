package com.mobile.anime.animerecommendations.service

import android.os.Handler
import com.squareup.otto.Bus
import android.os.Looper
import com.squareup.otto.ThreadEnforcer



/**
 * Created by rww306 on 2017-08-29.
 */

class HikariBus(enforcer: ThreadEnforcer) : Bus(enforcer) {
    private val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event)
        } else {
            mainThread.post{ post(event) }
        }
    }
}