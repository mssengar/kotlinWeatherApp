package com.mss.weatherapp.data.repositories

import com.mss.weatherapp.data.source.weather.WeatherDataSource
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.SearchResultModel
import com.mss.weatherapp.domain.models.WeatherData
import com.mss.weatherapp.domain.usecases.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {
    override suspend fun fetchWeatherData(city: String): Outcome<WeatherData.Daily> {
        return weatherDataSource.getCurrentWeatherData(city)
    }

    override suspend fun fetchHourlyWeatherData(
        city: String,
        noOfDays: Int
    ): Outcome<List<WeatherData.Daily>> {
        return weatherDataSource.getHourlyForecastData(city, noOfDays)
    }

    override suspend fun search(city: String): Outcome<List<SearchResultModel>> {
        return weatherDataSource.search(city)
    }
}