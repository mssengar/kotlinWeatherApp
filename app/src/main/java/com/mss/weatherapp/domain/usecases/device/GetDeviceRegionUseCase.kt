package com.mss.weatherapp.domain.usecases.device

import javax.inject.Inject

class GetDeviceRegionUseCase @Inject constructor(private  val repository: DeviceRepository) {
    operator fun invoke(): String {
        return repository.getDeviceRegion()
    }
}