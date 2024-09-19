package com.mss.weatherapp.domain.models

import com.mss.weatherapp.core.util.StringUtil

data class SearchResultModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
) {
    fun getDisplayName(delimiter: String): String {
        return StringUtil.getDisplayName(name, region, country, delimiter)
    }

    fun toFavoriteLocationModel(): FavoriteLocationModel {
        return FavoriteLocationModel(
            id = id,
            name = name,
            region = region,
            country = country
        )
    }
}