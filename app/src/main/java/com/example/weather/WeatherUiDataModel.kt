package com.example.weather

import androidx.annotation.DrawableRes

data class WeatherUiDataModel(
    val condition: String,
    @DrawableRes
    val conditionIconResId: Int,
    val temperature: Double,
    val localTime: String,
    val windSpeed: Double,
    val airPressure: Double,
    val humidity: Int
)

fun WeatherResponse.toUiDataModel(): WeatherUiDataModel {

    return WeatherUiDataModel(
        condition = this.current.condition.text,
        conditionIconResId = this.current.condition.code.getConditionImage(),
        airPressure = this.current.pressure_mb.toDouble(),
        windSpeed = this.current.wind_kph.toDouble(),
        localTime = this.location.localtime.takeLast(5),
        humidity = this.current.humidity.toInt(),
        temperature = this.current.temp_c.toDouble()
    )
}

private fun Int.getConditionImage(): Int {
    return when(this) {
        1000 -> R.drawable.clear_day
        1003 -> R.drawable.partly_cloudy_day
        1006 -> R.drawable.cloudy
        1009 -> R.drawable.overcast
        1030 -> R.drawable.fog
        1207 -> R.drawable.heavy_sleet
        1249, 1255, 1261 -> R.drawable.showers
        1252, 1258 -> R.drawable.heavy_showers

        else -> R.drawable.condition_example
    }
}