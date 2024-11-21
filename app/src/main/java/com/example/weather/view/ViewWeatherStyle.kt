package com.example.weather.view

import androidx.compose.ui.graphics.Color

abstract class ViewWeatherStyle {
    abstract val iconId: Int
    open val color: Color = Color.White
}