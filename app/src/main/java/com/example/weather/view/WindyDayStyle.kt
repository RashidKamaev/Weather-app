package com.example.weather.view

import androidx.compose.ui.graphics.Color
import com.example.weather.R

class WindyDayStyle: ViewWeatherStyle() {
    override val iconId: Int
        get() = R.drawable.windy

    override val color: Color = Color.Blue
}
