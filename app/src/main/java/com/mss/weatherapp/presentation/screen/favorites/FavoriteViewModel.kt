package com.mss.weatherapp.presentation.screen.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mss.weatherapp.core.util.DateUtil
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import com.mss.weatherapp.domain.usecases.device.GetSavedLocationsUseCase
import com.mss.weatherapp.domain.usecases.device.GetSystemCurrentTimeInMillisUseCase
import com.mss.weatherapp.domain.usecases.weather.FetchHourlyForecastDataUseCase
import com.mss.weatherapp.presentation.screen.home.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getSystemCurrentTimeInMillisUseCase: GetSystemCurrentTimeInMillisUseCase,
    val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    val forecastDataUseCase: FetchHourlyForecastDataUseCase,
) : ViewModel() {
    val dateState: MutableLiveData<String> = MutableLiveData("")

    val favoriteLocationDataList: MutableLiveData<List<FavoriteLocationModel>> =
        MutableLiveData(mutableListOf())

    init {
        dateState.value = DateUtil.getDateFromEpoch(getSystemCurrentTimeInMillisUseCase.invoke())
        refreshFavoriteData()
    }

    fun onFavoriteItemClicked(data: FavoriteLocationModel) {

    }

    private fun refreshFavoriteData() {
        if (getSavedLocationsUseCase.invoke().isEmpty()) return
        favoriteLocationDataList.value = getSavedLocationsUseCase.invoke()
        fetchWeatherData()

    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            getSavedLocationsUseCase.invoke().forEach {
                when (val forecastData = forecastDataUseCase.invoke(it.name, 3)) {
                    is Outcome.Success -> {
                        val currentList = favoriteLocationDataList.value
                        val newList = currentList?.map { favoriteLocationModel ->
                            if (forecastData.data.firstOrNull()
                                    ?.getIdentifier() == favoriteLocationModel.getIdentifier()
                            ) {
                                return@map FavoriteLocationModel(
                                    id = favoriteLocationModel.id,
                                    name = favoriteLocationModel.name,
                                    region = favoriteLocationModel.region,
                                    country = favoriteLocationModel.country,
                                    weatherData = WeatherData(
                                        precipitation = forecastData.data.firstOrNull()?.precipitation
                                            ?: 0f,
                                        wind = forecastData.data.firstOrNull()?.windSpeed ?: 0f,
                                        humidity = forecastData.data.firstOrNull()?.humidity ?: 0f,
                                        temperature = forecastData.data.firstOrNull()?.tempAverage
                                            ?: 0f
                                    )
                                )

                            } else favoriteLocationModel

                        }
                        favoriteLocationDataList.value = newList
                    }

                    is Outcome.Error -> {

                    }

                    is Outcome.Error -> TODO()
                    is Outcome.Success -> TODO()
                }
            }

        }

    }
}