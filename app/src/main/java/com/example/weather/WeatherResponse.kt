package com.example.weather

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: WeatherCurrentResponse
)

data class WeatherLocationResponse(
    val localtime: String
)

data class WeatherCurrentResponse(
    val temperature: Number,
    val condition: WeatherConditionResponse,
    val windKph: Number,
    val pressureMb : Number,
    val humidity: Int,
    val code: Int

)

data class WeatherConditionResponse(
    val text: String,
    val code: Int
)