package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.domain.models.FavoriteLocationModel
import javax.inject.Inject

class UpdateSavedLocationsUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(favoriteLocations: List<FavoriteLocationModel>) {
        return deviceRepository.updateSavedLocations(favoriteLocations.map { it.toSavedLocationModel() })
    }
}