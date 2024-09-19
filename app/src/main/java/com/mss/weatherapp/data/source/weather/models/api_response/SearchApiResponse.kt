package com.mss.weatherapp.data.source.weather.models.api_response

import com.mss.weatherapp.domain.models.SearchResultModel

data class SearchApiResponse(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val url: String
) {
    fun toSearchResultModel(): SearchResultModel {
        return SearchResultModel(
            id = id,
            name = name,
            region = region,
            country = country
        )
    }
}