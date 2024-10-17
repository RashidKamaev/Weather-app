@file:OptIn(ExperimentalGlancePreviewApi::class)

package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
            }
        }
    }
}

@Preview
@Composable
fun WeatherLocation(
    city: String, // Город
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 22.dp
            )
            .background(
                color = Color.Black.copy(0.05f),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 9.dp
                )
        ) {
        Text(
            text = city,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.vector_down),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(12.dp)
                    .clickable { onClick() }
            )
        }
    }
}

@Preview
@Composable
fun Temperature(
    temperature: Int,
) {
   Box(
       modifier = Modifier
           .height(320.dp)
           .width(320.dp)
   ) {
       Column(
           modifier = Modifier
               .fillMaxSize()

       ) {
           Icon(
               imageVector = ImageVector.vectorResource(id = R.drawable.partly_cloudy_day),
               contentDescription = null,
               modifier = Modifier
                   .fillMaxSize()
           )
           Text(
               text = "",
               fontSize = 30.sp,
               fontWeight = FontWeight.Medium,
               color = Color.White,
           )
           Text(
               text = "$temperature°C",
               fontSize = 70.sp,
               fontWeight = FontWeight.Medium,
               color = Color.White,
           )
       }
   }
}

@Preview
@Composable
fun WeatherDetails(
    localTime: String, // Местное время
    windSpeed: Double, // Скорость ветра
    airPressure: Int, // Давление
    humidity: Int // Влажность
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 44.dp
            )
            .background(
                color = Color.Black.copy(0.05f),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 15.dp,
                    vertical = 6.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShowBlock(
                title = "Время",
                subtitle = localTime
            )
            ShowBlock(
                title = "Ск. ветра",
                subtitle = "$windSpeed М/С"
            )
            ShowBlock(
                title = "Давление",
                subtitle = "$airPressure мм."
            )
            ShowBlock(
                title = "Влажность",
                subtitle = "$humidity %"
            )
        }
    }
}

@Preview
@Composable
fun ShowBlock(
    title: String, // Заголовок
    subtitle: String // Подзаголовок
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(0.7f),
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold)))
        )
        Text(
            text = subtitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
        )
    }
}
