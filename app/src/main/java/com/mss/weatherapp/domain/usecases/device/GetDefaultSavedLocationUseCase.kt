package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.domain.models.FavoriteLocationModel
import javax.inject.Inject

class GetDefaultSavedLocationUseCase @Inject constructor(private val deviceRepository: DeviceRepository) {
    operator fun invoke(): FavoriteLocationModel? {
        return deviceRepository.getDefaultSavedLocation()?.toFavoriteLocationModel()
    }
}