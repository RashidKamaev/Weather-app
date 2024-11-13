package com.example.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getWeatherData(
        @Query("key") key: String = "6c2318ffbcf44376b1e85601240811",
        @Query("q") city: String,
        @Query("aqi") aqi: String = "no"
    ): WeatherResponse
}