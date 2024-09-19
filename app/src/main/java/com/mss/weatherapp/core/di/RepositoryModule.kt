package com.mss.weatherapp.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mss.weatherapp.data.repositories.DeviceRepositoryImpl
import com.mss.weatherapp.data.repositories.WeatherRepositoryImpl
import com.mss.weatherapp.domain.usecases.device.DeviceRepository
import com.mss.weatherapp.domain.usecases.weather.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    fun bindDeviceRepository(deviceRepository: DeviceRepositoryImpl): DeviceRepository

}