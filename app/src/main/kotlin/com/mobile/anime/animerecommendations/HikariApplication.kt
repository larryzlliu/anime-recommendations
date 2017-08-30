package com.mobile.anime.animerecommendations

/**
 * Created by rww306 on 2017-08-29.
 */


import android.app.Application
import com.mobile.anime.animerecommendations.dependencies.DaggerHikariComponent
import com.mobile.anime.animerecommendations.dependencies.HikariComponent
import com.mobile.anime.animerecommendations.dependencies.HikariModule
import com.mobile.anime.animerecommendations.service.ServiceManager
import com.squareup.otto.Bus
import javax.inject.Inject

/**d
 * Created by rww306 on 2017-06-14.
 */

class HikariApplication : Application() {

    @Inject
    lateinit var serviceManager : ServiceManager

    var component: HikariComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerHikariComponent
                    .builder()
                    .hikariModule(HikariModule(this))
                    .build()

        component!!.inject(this)
    }
}