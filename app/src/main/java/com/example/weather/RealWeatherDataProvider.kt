package com.example.weather

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealWeatherDataProvider : WeatherDataProvider {
    override suspend fun getData(
        city : String
    ): WeatherData {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val weatherApi = retrofit.create(WeatherApi::class.java)
        val data = weatherApi.getWeatherData(city = city)
        Log.d("Data", "WeatherApp: $data ")
        return WeatherData(
            data.location.localtime,
            data.current.wind_kph,
            data.current.pressure_mb,
            data.current.humidity,
            data.current.temp_c
        )
    }
}