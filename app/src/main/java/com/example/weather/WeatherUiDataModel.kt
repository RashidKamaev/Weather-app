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
        /// ...
        else -> R.drawable.condition_example
    }
}