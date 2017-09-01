package com.mobile.anime.animerecommendations.util

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.google.gson.Gson


/**
 * Created by rww306 on 2017-09-01.
 */
private val LIST_ID = "list_id"

fun addAnime(id : Int, context: Context) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val listIds = getAnimeIds(context)
    listIds.add(id)
    addAnimeList(listIds, context)
}

fun addAnimeList(list: List<Int>, context: Context) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val gsonString = gson.toJson(list)
    editor.putString(LIST_ID, gsonString)
    editor.commit()
}

fun getAnimeIds(context: Context): ArrayList<Int> {
    val gson = Gson()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val listIds = sharedPreferences.getString(LIST_ID, "")
    val returnList = gson.fromJson<ArrayList<Int>>(listIds, ArrayList::class.java)
    return returnList
}

fun clearSharedPref(context: Context) {
    val listIds = getAnimeIds(context)
    listIds.clear()
    addAnimeList(listIds, context)
}


