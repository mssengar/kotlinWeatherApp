package com.mss.weatherapp.domain.usecases.weather

import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.SearchResultModel
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): Outcome<List<SearchResultModel>> {
        return weatherRepository.search(city)
    }
}