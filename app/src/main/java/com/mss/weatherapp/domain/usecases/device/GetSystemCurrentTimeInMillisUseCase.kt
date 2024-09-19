package com.mss.weatherapp.domain.usecases.device

import com.mss.weatherapp.data.source.device.DeviceDataSource
import javax.inject.Inject

class GetSystemCurrentTimeInMillisUseCase @Inject constructor(private val deviceDataSource: DeviceDataSource) {
    operator fun invoke(): Long {
        return deviceDataSource.getSystemCurrentTimeInMillis()
    }
}