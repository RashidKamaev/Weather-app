package com.example.weather

class MockWeatherDataProvider : WeatherDataProvider {
    override fun getData(): WeatherData {
        return WeatherData(
            "09:11",
            24.5,
            35,
            354,
            36
        )
    }
}