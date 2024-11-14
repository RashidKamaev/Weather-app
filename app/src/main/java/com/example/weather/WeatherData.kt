package com.example.weather

data class WeatherData(
    val localTime : String,
    val windSpeed : Number,
    val airPressure : Number,
    val humidity : Int,
    var temperature : Double,
)