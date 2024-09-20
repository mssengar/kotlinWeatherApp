package com.mss.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import com.mss.weatherapp.domain.usecases.device.GetDeviceRegionUseCase
import com.mss.weatherapp.domain.usecases.device.GetSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.device.UpdateSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.weather.SearchCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val deviceRegionUseCase: GetDeviceRegionUseCase,
    val searchCityUseCase: SearchCityUseCase,
    val updateSavedLocationsUseCase: UpdateSavedLocationsUseCase,
    val getSavedLocationsUseCase: GetSavedLocationsUseCase
) : ViewModel() {
    val onNavigateToSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val onNavigateToFavorites: MutableLiveData<Boolean> = MutableLiveData(false)
    val onNavigateBack: MutableLiveData<Boolean> = MutableLiveData(false)
    val onShowWeatherDetail: MutableLiveData<FavoriteLocationModel> = MutableLiveData()

    init {
//        refreshFavoriteLocationData()
    }

    private fun refreshFavoriteLocationData() {
        if (getSavedLocationsUseCase.invoke().isNotEmpty()) return
        val deviceLocation = deviceRegionUseCase.invoke()
        viewModelScope.launch {
            val result = searchCityUseCase.invoke(deviceLocation)
            when (result) {
                is Outcome.Success -> {
                    val searchResultModel = result.data.firstOrNull()
                    if (searchResultModel == null) {
                        Log.e("Weather", "Location data could not be determined")
                    } else {
                        updateSavedLocationsUseCase.invoke(listOf(
                            FavoriteLocationModel(
                            id = searchResultModel.id,
                            name = searchResultModel.name,
                            region = searchResultModel.region,
                            country = searchResultModel.country,
                            isDefault = true
                        )
                        ))
                        Log.e("Weather", "Location data successfully saved")
                    }
                }

                is Outcome.Error -> {
                    Log.e("Weather", "Unknown network error")
                }

                else -> {}
            }
        }

    }


    fun onNavigateToSearch() {
        onNavigateToSearch.value = true
    }

    fun onNavigateToSearchResolved() {
        onNavigateToSearch.value = false
    }

    fun onNavigateToFavorites() {
        onNavigateToFavorites.value = true
    }

    fun onNavigateToFavoritesResolved() {
        onNavigateToFavorites.value = false
    }

    fun onNavigateBack() {
        onNavigateBack.value = true
    }

    fun onNavigateBackResolved() {
        onNavigateBack.value = false
    }

    fun onShowWeatherDetail(locationModel: FavoriteLocationModel) {
        onShowWeatherDetail.value = locationModel

    }

    fun onShowWeatherDetailResolved() {
        onShowWeatherDetail.value = null
    }


}