package com.mss.weatherapp.presentation.screen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.SearchResultModel
import com.mss.weatherapp.domain.usecases.device.GetDeviceRegionUseCase
import com.mss.weatherapp.domain.usecases.weather.SearchCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val getDeviceRegionUseCase: GetDeviceRegionUseCase
) :
    ViewModel() {
    val searchString: MutableLiveData<String> = MutableLiveData("")
    val autocompleteResult: MutableLiveData<List<SearchResultModel>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData("")
    val isClearSearchIconVisible: MutableLiveData<Boolean> = MutableLiveData(false)


    fun onSearchStringChanged(newString: String) {
        isClearSearchIconVisible.value = newString.isNotEmpty()
        searchString.value = newString
        if (newString.trim().length > 2) {
            performSearch(newString)
        } else {
            autocompleteResult.value = mutableListOf()
        }
    }

    private fun performSearch(searchString: String) {
        viewModelScope.launch {
            val response = searchCityUseCase.invoke(searchString)
            if (response is Outcome.Success) {
                autocompleteResult.value = response.data
            }
            if (response is Outcome.Error) {
                errorMessage.value = response.exception.message
            }
        }
    }

    fun onSearchRecommendationItemClicked(result: SearchResultModel) {
        onSearchFieldValueCleared()
    }

    fun onSearchFieldValueCleared() {
        searchString.value = ""
        autocompleteResult.value = mutableListOf()
    }
}