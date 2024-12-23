package com.example.weather

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: WeatherCurrentResponse
)

data class WeatherLocationResponse(
    val localtime: String
)

data class WeatherCurrentResponse(
    val temp_c: Number,
    val condition: WeatherConditionResponse,
    val wind_kph: Number,
    val pressure_mb : Number,
    val humidity: Int
)

data class WeatherConditionResponse(
    val text: String
)