package com.mss.weatherapp.core.util

import com.google.gson.Gson
import com.mss.weatherapp.domain.models.FavoriteLocationModel

fun String.toFavoriteLocationModel(): FavoriteLocationModel {
    val converter = Gson()
    return converter.fromJson(this, FavoriteLocationModel::class.java)
}