package com.mss.weatherapp.domain.usecases.weather

import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.WeatherData
import javax.inject.Inject

class FetchHourlyForecastDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String, noOfDays: Int): Outcome<List<WeatherData.Daily>> {
        return weatherRepository.fetchHourlyWeatherData(city, noOfDays)

    }
}