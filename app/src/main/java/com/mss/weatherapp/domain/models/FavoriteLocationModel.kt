package com.mss.weatherapp.domain.models

import com.google.gson.Gson
import com.mss.weatherapp.data.source.device.model.SavedLocationModel
import com.mss.weatherapp.presentation.screen.home.model.WeatherData

data class FavoriteLocationModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val isDefault: Boolean = false,
    val weatherData: WeatherData? = null
) {
    fun getDisplayName(): String {
        return "$name,\n$country"
    }

    fun getIdentifier(): String {
        return "$name $region $country"
    }

    fun toSavedLocationModel(): SavedLocationModel {
        return SavedLocationModel(
            id = id,
            name = name,
            region = region,
            country = country,
            isDefault = isDefault
        )
    }

     fun toStringArg(): String {
        return Gson().toJson(this)
    }
}