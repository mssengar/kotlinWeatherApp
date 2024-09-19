package com.mss.weatherapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mss.weatherapp.domain.usecases.device.DeviceRepository
import com.mss.weatherapp.domain.usecases.device.GetDeviceRegionUseCase
import com.mss.weatherapp.domain.usecases.weather.SearchCityUseCase
import com.mss.weatherapp.domain.usecases.weather.WeatherRepository
import io.mockk.mockk
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class BaseViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var searchCityUseCase: SearchCityUseCase

    @Mock
    lateinit var getDeviceRegionUseCase: GetDeviceRegionUseCase

    var weatherRepository: WeatherRepository = mockk(relaxed = true)
    var deviceRepository: DeviceRepository = mockk(relaxed = true)

    open fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
}