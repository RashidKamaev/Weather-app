package com.example.weather

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealWeatherDataProvider : WeatherDataProvider {
    private var weatherApi: WeatherApi

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    override suspend fun getData(city: String): WeatherData {
        val data = weatherApi.getWeatherData(city = city)
        Log.d("Data", "WeatherApp: $data ")
        return WeatherData(
            data.location.localtime,
            data.current.windKph,
            data.current.pressureMb,
            data.current.humidity,
            data.current.temperature.toDouble(),
            data.current.code
            )
    }
}
