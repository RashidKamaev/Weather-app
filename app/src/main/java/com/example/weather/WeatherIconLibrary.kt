package com.example.weather

object WeatherIconLibrary {
    fun getIconResource(code: Int): Int {
    return when (code) {
        1000 -> R.drawable.clear_day
        1003 -> R.drawable.partly_cloudy_day
        1006 -> R.drawable.cloudy
        1009 -> R.drawable.overcast
        1030 -> R.drawable.fog
        1207 -> R.drawable.heavy_sleet
        1249, 1255, 1261 -> R.drawable.showers
        1252, 1258 -> R.drawable.heavy_showers
        else -> R.drawable.overcast
        }
    }
}
