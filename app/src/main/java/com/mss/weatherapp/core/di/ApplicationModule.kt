package com.mss.weatherapp.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.mss.weatherapp.WeatherApplication

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    fun providesWeatherApplication(@ApplicationContext context: Context): WeatherApplication {
        return context as WeatherApplication
    }
}