package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.domain.models.FavoriteLocationModel
import javax.inject.Inject

class GetSavedLocationsUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): List<FavoriteLocationModel> {
        return deviceRepository.getSavedLocations().map {
            it.toFavoriteLocationModel()
        }
    }

}