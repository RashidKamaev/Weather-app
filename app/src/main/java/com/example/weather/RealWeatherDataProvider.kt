package com.example.weather

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealWeatherDataProvider() : WeatherDataProvider {
    var weatherApi: WeatherApi

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
            data.current.wind_kph,
            data.current.pressure_mb,
            data.current.humidity,
            data.current.temp_c.toDouble()
        )
    }
}
