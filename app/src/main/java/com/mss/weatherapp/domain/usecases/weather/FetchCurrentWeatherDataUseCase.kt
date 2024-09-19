package com.mss.weatherapp.domain.usecases.weather

import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.WeatherData
import javax.inject.Inject

class FetchCurrentWeatherDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): Outcome<WeatherData.Daily> {
        return weatherRepository.fetchWeatherData(city)

    }

}