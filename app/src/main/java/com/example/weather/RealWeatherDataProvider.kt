package com.example.weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class RealWeatherDataProvider : WeatherDataProvider {

    private val weatherApi: WeatherApi = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    override suspend fun getData(city: String): WeatherData {
        val data = weatherApi.getWeatherData(city = city)

        val windSpeedMs = data.current.wind_kph.toDouble() / 3.6
        val pressureMmHg = data.current.pressure_mb.toDouble() * 0.750061683

        return WeatherData(
            localTime = data.location.localtime
                .substringAfter(" ")
                .take(5),

            windSpeed = (windSpeedMs * 10).roundToInt() / 10.0,

            airPressure = pressureMmHg.roundToInt(),

            humidity = data.current.humidity,

            temperature = data.current.temp_c.toDouble(),

            conditionText = data.current.condition.text,

            conditionCode = data.current.condition.code
        )
    }
}