package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.data.source.device.DeviceDataSource
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import javax.inject.Inject

class DeleteSavedLocationUseCase @Inject constructor(private val deviceDataSource: DeviceDataSource) {
    operator fun invoke(locations: List<FavoriteLocationModel>) {
        deviceDataSource.deleteSavedLocations(locations = locations.map { it.toSavedLocationModel()})
    }
}