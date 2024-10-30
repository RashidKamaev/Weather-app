package com.example.weather.ui.theme

import android.health.connect.datatypes.units.Temperature

data class WeatherData(
    val localTime: String,
    val windSpeed: Double,
    val airPressure: Int,
    val humidity: Int,
    val temperature: Int,
)