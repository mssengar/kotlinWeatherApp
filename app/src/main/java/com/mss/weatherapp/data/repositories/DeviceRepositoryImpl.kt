package com.mss.weatherapp.data.repositories

import com.mss.weatherapp.data.source.device.DeviceDataSource
import com.mss.weatherapp.data.source.device.model.SavedLocationModel
import com.mss.weatherapp.domain.usecases.device.DeviceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepositoryImpl @Inject constructor(private val deviceDataSource: DeviceDataSource) :
    DeviceRepository {
    override fun getSavedLocations(): List<SavedLocationModel> {
        return deviceDataSource.getSavedLocations()
    }

    override fun updateSavedLocations(locations: List<SavedLocationModel>) {
         deviceDataSource.updateSavedLocations(locations = locations)
    }

    override fun deleteSavedLocation(locations: List<SavedLocationModel>) {
        deviceDataSource.deleteSavedLocations(locations = locations)
    }

    override fun clearSavedLocations() {
        deviceDataSource.clearSavedLocations()
    }

    override fun getDefaultSavedLocation(): SavedLocationModel? {
       return deviceDataSource.getDefaultSavedLocation()
    }

    override fun getDeviceRegion(): String {
        return deviceDataSource.getDeviceRegion()
    }

    override fun getSystemCurrentTimeInMillis(): Long {
        return deviceDataSource.getSystemCurrentTimeInMillis()
    }
}
