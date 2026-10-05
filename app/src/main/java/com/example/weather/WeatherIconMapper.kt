package com.example.weather

import androidx.annotation.DrawableRes

@DrawableRes
fun getWeatherIcon(
    conditionCode: Int,
    windSpeed: Double
): Int {

    if (
        windSpeed >= 10.0 &&
        conditionCode in setOf(1000, 1003, 1006, 1009)
    ) {
        return R.drawable.windy
    }

    return when (conditionCode) {

        1000 -> R.drawable.clear_day

        1003 -> R.drawable.partly_cloudy_day

        1006 -> R.drawable.cloudy

        1009 -> R.drawable.overcast

        1012, 1015, 1018, 1021, 1024, 1027,
        1030, 1033, 1036, 1039, 1042, 1045, 1048,
        1135, 1147 -> R.drawable.fog

        1063,
        1150, 1153,
        1180, 1183,
        1240 -> R.drawable.showers

        1186, 1189,
        1192, 1195,
        1243, 1246 -> R.drawable.heavy_showers

        1069, 1072,
        1168,
        1198,
        1204,
        1237,
        1249,
        1261 -> R.drawable.sleet

        1171,
        1201,
        1207,
        1252,
        1264 -> R.drawable.heavy_sleet

        1066,
        1210, 1213,
        1216, 1219,
        1255 -> R.drawable.snow

        1114, 1117,
        1222, 1225,
        1258 -> R.drawable.heavy_snow

        1087,
        1273, 1276 -> R.drawable.thunderstorm_showers

        1279, 1282 -> R.drawable.thunderstorm_snow

        else -> R.drawable.partly_cloudy_day
    }
}