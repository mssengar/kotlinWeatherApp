package com.mss.weatherapp.data.source.weather

import android.util.Log
import com.mss.weatherapp.domain.Outcome
import com.mss.weatherapp.domain.models.SearchResultModel
import com.mss.weatherapp.domain.models.WeatherData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataSource @Inject constructor() {
    companion object {
        const val TEST_BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val API_BASE_URL = "https://api.weatherapi.com/v1/"
        const val API_KEY = "9b13427bccca4794814152219230808"
    }

    private val weatherApiService: WeatherApiService
    private val httpClient: OkHttpClient

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        httpClient = okHttpClientBuilder.addNetworkInterceptor(Interceptor { chain ->
            with(chain.request()) {
                chain.proceed(
                    this.newBuilder().url(
                        this.url.newBuilder()
                            .addQueryParameter("key", API_KEY).build()
                    ).build()
                )
            }
        }).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApiService = retrofit.create(WeatherApiService::class.java)
    }

    suspend fun getCurrentWeatherData(city: String): Outcome<WeatherData.Daily> {
        val response = weatherApiService.getCurrentWeatherData(city)
        Log.e("API Response", response.body().toString())
        if (response.isSuccessful) {
            response.body()?.toWeatherData()?.let {
                return Outcome.Success(it)
            }
        } else {
            return Outcome.Error(Exception(response.errorBody().toString()))
        }
        return Outcome.Error(Exception("Unknown error"))
    }

    suspend fun getHourlyForecastData(
        city: String,
        noOfDays: Int
    ): Outcome<List<WeatherData.Daily>> {
        val response = weatherApiService.getHourlyForecastData(city, noOfDays)
        Log.e("API Response", response.body().toString())
        if (response.isSuccessful) {
            response.body()?.toForecastData()?.let {
                return Outcome.Success(it)
            }
        } else {
            return Outcome.Error(Exception(response.errorBody().toString()))
        }
        return Outcome.Error(Exception("Unknown error"))
    }

    suspend fun search(city: String): Outcome<List<SearchResultModel>> {
        val response = weatherApiService.searchCity(city)
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                return Outcome.Success(responseBody.map { item ->
                    item.toSearchResultModel()
                })
            }

        } else {

            return Outcome.Error(Exception(response.errorBody().toString()))
        }
        return Outcome.Error(Exception("Unknown error"))
    }
}