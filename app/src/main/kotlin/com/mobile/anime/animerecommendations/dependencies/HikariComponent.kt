package com.mobile.anime.animerecommendations.dependencies

import com.mobile.anime.animerecommendations.HikariApplication
import com.mobile.anime.animerecommendations.MainActivity
import com.mobile.anime.animerecommendations.view.AnimeCard
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rww306 on 2017-08-29.
 */

@Singleton
@Component(modules = arrayOf(HikariModule::class))

interface HikariComponent {
    fun inject(app : HikariApplication)
    fun inject(activity: MainActivity)
    fun inject(card: AnimeCard)
}
