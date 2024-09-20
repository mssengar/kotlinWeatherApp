package com.mss.weatherapp.presentation.screen.weather_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mss.weatherapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mss.weatherapp.core.util.toFavoriteLocationModel
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import com.mss.weatherapp.domain.usecases.device.DeleteSavedLocationUseCase
import com.mss.weatherapp.domain.usecases.device.GetDefaultSavedLocationUseCase
import com.mss.weatherapp.domain.usecases.device.GetDeviceRegionUseCase
import com.mss.weatherapp.domain.usecases.device.GetSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.device.GetSystemCurrentTimeInMillisUseCase
import com.mss.weatherapp.domain.usecases.device.UpdateSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.weather.FetchHourlyForecastDataUseCase
import com.mss.weatherapp.domain.usecases.weather.SearchCityUseCase
import com.mss.weatherapp.presentation.screen.WeatherNavigationRoute
import com.mss.weatherapp.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchHourlyForecastDataUseCase: FetchHourlyForecastDataUseCase,
    getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    deviceRegionUseCase: GetDeviceRegionUseCase,
    getSavedLocationsUseCase: GetSavedLocationsUseCase,
    getDefaultSavedLocationUseCase: GetDefaultSavedLocationUseCase,
    updateSavedLocationsUseCase: UpdateSavedLocationsUseCase,
    val deleteSavedLocationUseCase: DeleteSavedLocationUseCase,

    searchCityUseCase: SearchCityUseCase

) : HomeViewModel(
    savedStateHandle,
    fetchHourlyForecastDataUseCase,
    getSystemCurrentTimeInMillisUseCase,
    deviceRegionUseCase,
    getDefaultSavedLocationUseCase,
    getSavedLocationsUseCase,
    searchCityUseCase,
    updateSavedLocationsUseCase
) {

    private lateinit var favoriteLocationModel: FavoriteLocationModel

    init {
        fetchWeatherDetail()
    }

    val favoriteIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.ic_heart)

    private fun fetchWeatherDetail() {

        val locationString: String =
            savedStateHandle[WeatherNavigationRoute.SearchResult.argumentName] ?: ""

        favoriteLocationModel = locationString.toFavoriteLocationModel()
        viewModelScope.launch {
            val response =
                fetchHourlyForecastDataUseCase.invoke(favoriteLocationModel.getDisplayName(), 5)
            if (response is Outcome.Success) {
                filterForecastedWeatherResult(response.data)
                checkIfIsFavorite(response.data.firstOrNull()?.getIdentifier() ?: "")
            }
        }
    }

    private fun checkIfIsFavorite(locationIdentifier: String) {
        val currentlySavedLocations = getSavedLocationsUseCase.invoke()
        currentlySavedLocations.firstOrNull() {
            it.getIdentifier() == locationIdentifier
        }.apply {
            if (this != null) {
                favoriteIcon.value = R.drawable.ic_heart_filled
            } else {
                favoriteIcon.value = R.drawable.ic_heart
            }
        }
    }

    fun onSaveLocationTapped() {
        val currentlySavedLocations = getSavedLocationsUseCase.invoke()
        currentlySavedLocations.firstOrNull() { locationModel ->
            locationModel.getIdentifier() == favoriteLocationModel.getIdentifier()
        }.apply {
            if (this == null) {
                updateSavedLocationsUseCase.invoke(listOf(favoriteLocationModel))
            } else {
                deleteSavedLocationUseCase.invoke(listOf(favoriteLocationModel))
            }
            checkIfIsFavorite(favoriteLocationModel.getIdentifier())

        }
    }

}