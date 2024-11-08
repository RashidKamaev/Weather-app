package com.example.weather

data class WeatherData(
    val localTime : String,
    val windSpeed : Double,
    val airPressure : Int,
    val humidity : Int,
    val temperature : Int,
)