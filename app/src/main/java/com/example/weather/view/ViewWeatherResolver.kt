package com.example.weather.view

import com.example.weather.WeatherData

class ViewWeatherResolver {
    fun resolve(weatherData: WeatherData) : ViewWeatherStyle {
        if (weatherData.temperature.toFloat() < 0)
        {
            return SnowDayStyle()
        }
        if (weatherData.windSpeed.toFloat() < 5)
        {
            return ClearDayStyle()
        }
        if (weatherData.windSpeed.toFloat() >= 5 ){
            return WindyDayStyle()
        }
        if (weatherData.windSpeed.toFloat() >= 10){
            return WindyDayStyle()
        }
        if (weatherData.humidity.toFloat() >= 85){
            return FogDayStyle()
        }
        if (weatherData.humidity.toFloat() >= 100){
            return HeavyShowersDayStyle()
        }
        // расширить остальными стилями
        return DefaultStyle()
    }
}