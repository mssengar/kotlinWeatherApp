package com.mss.weatherapp.domain

sealed class Outcome<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : Outcome<T>()
    data class Error(val exception: Exception) : Outcome<Nothing>()
}