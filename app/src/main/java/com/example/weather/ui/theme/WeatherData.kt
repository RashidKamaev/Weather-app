package com.example.weather.ui.theme

class WeatherData(
    val localTime: String,
    val windSpeed: Double,
    val airPressure: Int,
    val humidity: Int
)
val weatherData = WeatherData(
    localTime = "13:12",
    windSpeed = 24.5,
    airPressure = 35,
    humidity = 354
)

val time = weatherData.localTime
val speed = weatherData.windSpeed
val pressure = weatherData.airPressure
val humidity = weatherData.humidity
