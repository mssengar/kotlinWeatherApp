package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.data.source.device.model.SavedLocationModel

interface DeviceRepository {

    fun getSavedLocations(): List<SavedLocationModel>

    fun updateSavedLocations(locations: List<SavedLocationModel>)

    fun deleteSavedLocation(locations: List<SavedLocationModel>)

    fun clearSavedLocations()

    fun getDefaultSavedLocation(): SavedLocationModel?


    fun getDeviceRegion():String

    fun getSystemCurrentTimeInMillis(): Long
}