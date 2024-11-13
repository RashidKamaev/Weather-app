package com.example.weather

interface WeatherDataProvider {
    suspend fun getData(city: String) : WeatherData
}