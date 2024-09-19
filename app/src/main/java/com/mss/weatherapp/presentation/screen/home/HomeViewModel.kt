package com.mss.weatherapp.presentation.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import com.mss.weatherapp.domain.models.WeatherData
import com.mss.weatherapp.domain.usecases.device.GetDefaultSavedLocationUseCase
import com.mss.weatherapp.domain.usecases.device.GetDeviceRegionUseCase
import com.mss.weatherapp.domain.usecases.device.GetSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.device.GetSystemCurrentTimeInMillisUseCase
import com.mss.weatherapp.domain.usecases.device.UpdateSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.weather.FetchHourlyForecastDataUseCase
import com.mss.weatherapp.domain.usecases.weather.SearchCityUseCase
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val fetchHourlyForecastDataUseCase: FetchHourlyForecastDataUseCase,
    private val getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    private val deviceRegionUseCase: GetDeviceRegionUseCase,
    private val getDefaultSavedLocationUseCase: GetDefaultSavedLocationUseCase,
    val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    val searchCityUseCase: SearchCityUseCase,
    val updateSavedLocationsUseCase: UpdateSavedLocationsUseCase
) : ViewModel() {
    val forecastedWeatherData: MutableLiveData<List<WeatherData.Daily>> = MutableLiveData()

    init {
        if (getDefaultSavedLocationUseCase.invoke() == null) {
            refreshFavoriteLocationData()
        } else {
            fetchDefaultLocationWeatherData()

        }
    }


    private fun fetchDefaultLocationWeatherData() {
        var location = getDefaultSavedLocationUseCase.invoke()
        if (location == null) {
            refreshFavoriteLocationData()
            location = getDefaultSavedLocationUseCase.invoke()
        }
        val searchString = "${location?.name} ${location?.country}"
        viewModelScope.launch {
            val response =
                fetchHourlyForecastDataUseCase.invoke(searchString, 5)
            if (response is Outcome.Success) {
                filterForecastedWeatherResult(response.data)
            }
        }
    }


    fun filterForecastedWeatherResult(result: List<WeatherData.Daily>) {
        val filteredHourlyData = result[0].hourlyData?.toMutableList()
        filteredHourlyData?.removeIf {
            TimeUnit.SECONDS.toMillis(it.timeEpoch) < getSystemCurrentTimeInMillisUseCase.invoke()
        }
        val newList = mutableListOf<WeatherData.Daily>()
        result.forEachIndexed { index, item ->
            newList.add(
                WeatherData.Daily(
                    location = item.location,
                    country = item.country,
                    localTime = item.localTime,
                    localTimeEpoch = item.localTimeEpoch,
                    updatedAtEpoch = item.updatedAtEpoch,
                    updatedAtTimeString = item.updatedAtTimeString,
                    tempAverage = item.tempAverage,
                    tempMinimum = item.tempMinimum,
                    tempMaximum = item.tempMaximum,
                    isDay = item.isDay,
                    conditionDescription = item.conditionDescription,
                    windSpeed = item.windSpeed,
                    windDegree = item.windDegree,
                    precipitation = item.precipitation,
                    humidity = item.humidity,
                    cloud = item.cloud,
                    hourlyData = if (index == 0) filteredHourlyData else item.hourlyData,
                    region = item.region,
                    date = item.date
                )
            )
        }
        forecastedWeatherData.value = newList

    }

    private fun refreshFavoriteLocationData() {
        val deviceLocation = deviceRegionUseCase.invoke()
        viewModelScope.launch {
            val result = searchCityUseCase.invoke(deviceLocation)
            when (result) {
                is Outcome.Success -> {
                    val searchResultModel = result.data.firstOrNull()
                    if (searchResultModel == null) {
                        Log.e("Weather", "Location data could not be determined")
                    } else {
                        updateSavedLocationsUseCase.invoke(
                            listOf(
                                FavoriteLocationModel(
                                    id = searchResultModel.id,
                                    name = searchResultModel.name,
                                    region = searchResultModel.region,
                                    country = searchResultModel.country,
                                    isDefault = true
                                )
                            )
                        )
                        Log.e("Weather", "Location data successfully saved")
                        fetchDefaultLocationWeatherData()
                    }
                }

                is Outcome.Error -> {
                    Log.e("Weather", "Unknown network error")
                }

                else -> {}
            }
        }

    }


}

