package com.mss.weatherapp.domain.usecases.weather

import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.SearchResultModel
import com.mss.weatherapp.domain.models.WeatherData

interface WeatherRepository {
    suspend fun fetchWeatherData(city: String): Outcome<WeatherData.Daily>
    suspend fun fetchHourlyWeatherData(city: String, noOfDays:Int): Outcome<List<WeatherData.Daily>>

    suspend fun search(city:String): Outcome<List<SearchResultModel>>
}