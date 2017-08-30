package com.mobile.anime.animerecommendations.dependencies

import android.app.Application
import com.mobile.anime.animerecommendations.service.HikariBus
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rww306 on 2017-08-29.
 */
@Module
class HikariModule(app : Application){

    private var application : Application? = null

    init {
        this.application = app
    }

    @Provides
    @Singleton
    fun ottoBus() : Bus {
        return HikariBus(ThreadEnforcer.ANY)
    }
}