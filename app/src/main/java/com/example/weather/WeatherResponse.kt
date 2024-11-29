package com.example.weather

import android.health.connect.datatypes.units.Temperature

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: WeatherCurrentResponse,
)

data class WeatherLocationResponse(
    val localtime: String
)

data class WeatherCurrentResponse(
    val temperature: Temperature,
    val condition: WeatherConditionResponse,
    val wind_kph: Number,
    val pressure_mb : Number,
    val humidity: Int,
    val code: Int
)

data class WeatherConditionResponse(
    val text: String,
    val code: Int
)