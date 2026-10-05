@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.WeatherTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import kotlin.math.roundToInt
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val selectedItem = remember {
        mutableStateOf(CityBuiltIn.getDefaultCity())
    }

    val weatherData = remember {
        mutableStateOf<WeatherData?>(null)
    }

    val errorMessage = remember {
        mutableStateOf<String?>(null)
    }

    val weatherProvider = remember {
        RealWeatherDataProvider()
    }

    LaunchedEffect(selectedItem.value) {
        errorMessage.value = null

        try {
            weatherData.value = weatherProvider.getData(
                city = selectedItem.value
            )
        } catch (e: Exception) {
            errorMessage.value = "Не удалось загрузить погоду"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CityDropdown(
            modifier = Modifier.fillMaxWidth(),
            selectedItem = selectedItem.value,
            items = CityBuiltIn.getCities(),
            onSelect = { city ->
                selectedItem.value = city
            }
        )

        when {
            weatherData.value != null -> {
                Temperature(
                    weather = weatherData.value!!
                )
            }

            errorMessage.value != null -> {
                Text(
                    text = errorMessage.value!!
                )
            }

            else -> {
                Text(
                    text = "Загрузка..."
                )
            }
        }

        weatherData.value?.let { weather ->
            WeatherDetails(weather)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropdown(
    modifier : Modifier = Modifier,
    selectedItem : String,
    items : List<String>,
    onSelect : (String) -> Unit
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp, 22.dp)
            .background(
                color = Color.Black.copy(0.05f),
                shape = RoundedCornerShape(12.dp)
            ),
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
        content = {
            WeatherLocation(
                modifier = Modifier.menuAnchor(),
                city = selectedItem,
                isExpanded = isExpanded.value
            )
            ExposedDropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onSelect(item)
                            isExpanded.value = false
                        }
                    )
                }
            }

        }
    )
}

@Composable
fun WeatherDetails(
    weather : WeatherData
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
                subtitle = weather.localTime
            )
            ShowBlock(
                title = "Ск. ветра",
                subtitle = "${weather.windSpeed} м/с"
            )
            ShowBlock(
                title = "Давление",
                subtitle = "${weather.airPressure} мм."
            )
            ShowBlock(
                title = "Влажность",
                subtitle = "${weather.humidity} %"
            )
        }
    }
}

@Composable
fun Temperature(
    weather: WeatherData
) {
    val weatherIcon = getWeatherIcon(
        conditionCode = weather.conditionCode,
        windSpeed = weather.windSpeed
    )
    val temperature = weather.temperature.roundToInt()
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(Color(0xB29BB7F2)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                horizontal = 12.dp,
                vertical = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = weatherIcon),
                contentDescription = weather.conditionText,
                modifier = Modifier.size(78.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = weather.conditionText,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily(
                    listOf(
                        Font(R.font.montserrat_semibold)
                    )
                ),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${if (temperature > 0) "+" else ""}$temperature°",
                fontSize = 48.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(
                    listOf(
                        Font(R.font.montserrat_medium)
                    )
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherLocation(
    modifier : Modifier = Modifier,
    city : String,
    isExpanded : Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp,
                vertical = 9.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = city,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium))),
            color = Color.Black,
        )
        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
    }
}

@Composable
fun ShowBlock(
    title : String,
    subtitle : String
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

//@Preview(showBackground = true)
//@Composable
//fun WeatherDetailsPreview() {
//    WeatherTheme {
//
//        val weatherData = WeatherData(
//            localTime = "09:11",
//            windSpeed = 24.5,
//            airPressure = 35,
//            humidity = 354,
//            temperature = 36
//        )
//
//        WeatherDetails(weatherData)
//    }
//}



@Preview(showBackground = true)
@Composable
private fun DropdownMenuExamplePreview() {
    WeatherTheme {
        run {
            val selectedItem = remember {
                mutableStateOf(CityBuiltIn.getDefaultCity())
            }
            CityDropdown(
                modifier = Modifier
                    .fillMaxWidth(),
                selectedItem = selectedItem.value,
                items = CityBuiltIn.getCities(),
                onSelect = { selectedItem.value = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    WeatherTheme {
//        val weatherData = WeatherResponse(
//            temp_c = ,
//            windSpeed = ,
//            airPressure = ,
//            humidity = ,
//            temperature =
//        )
//        MainScreen(weatherData)
    }
}
