@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.runBlocking

//@Composable
//fun WeatherApp(){
//    LaunchedEffect(Unit) {
//        val provider: WeatherDataProvider = RealWeatherDataProvider()
//        val data: WeatherData = provider.getData(city = CityBuiltIn.getLondon())
//    }
//}

@Composable
fun WeatherApp() {
    val weatherData = remember { mutableStateOf<WeatherData?>(null) }
    val provider: WeatherDataProvider = RealWeatherDataProvider()

    LaunchedEffect(Unit) {
        weatherData.value = provider.getData(city = CityBuiltIn.getLondon())
    }
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    var serverData by remember { mutableStateOf<WeatherUiDataModel?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    weatherData.value?.let {
        MainScreen(it)
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                val weatherData : WeatherData
                runBlocking {
                    val weatherProvider : WeatherDataProvider =RealWeatherDataProvider()
                    weatherData = weatherProvider.getData(city = CityBuiltIn.getLondon())
                }
                MainScreen(weatherData)
                WeatherApp()
            }
        }
    }
}

@Composable
fun MainScreen(data : WeatherData) {
    val selectedItem = remember {
        mutableStateOf(CityBuiltIn.getDefaultCity())
    }
    var temperature: Double = 0.0
//    var weatherData: WeatherData
//    var myWeather =  mutableStateOf(value = weatherData)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CityDropdown(
            modifier = Modifier.fillMaxWidth(),
            selectedItem = selectedItem.value,
            items = CityBuiltIn.getCities(),
            onSelect = {
                selectedItem.value = it
                runBlocking {
                    val weatherProvider : WeatherDataProvider = RealWeatherDataProvider()
                    val weatherData = weatherProvider.getData(city = it)
                    temperature = weatherData.temperature as Double
                }
            }
        )
        val myTemperature = temperature
        Temperature(temperature.toString())
        WeatherDetails(data)
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
        onExpandedChange = {
            isExpanded.value = it
                           },
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
    temperature : String
) {
    Box(
        modifier = Modifier
            .height(320.dp)
            .width(172.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.partly_cloudy_day),
                contentDescription = null,
                modifier = Modifier
            )
            Text(
                text = "Облачно",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold))),
                color = Color.Black,
            )
            Text(
                text = "$temperature°",
                fontSize = 70.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium))),
                color = Color.Black,
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

@Preview(showBackground = true)
@Composable
fun WeatherDetailsPreview() {
    WeatherTheme {

        val weatherData = WeatherData(
            localTime = "09:11",
            windSpeed = 24.5,
            airPressure = 35,
            humidity = 354,
            temperature = 36.0
        )

        WeatherDetails(weatherData)
    }
}



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
