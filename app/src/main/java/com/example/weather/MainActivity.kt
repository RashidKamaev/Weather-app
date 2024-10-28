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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            CityDropdown(
                modifier = Modifier
                    .fillMaxWidth(),
                selectedItem = "Москва",
                items = listOf("Самара", "Москва", "Владивосток"),
                onSelect = {}
            )
        Temperature(
            temperature = 36
        )
            WeatherDetails(
                modifier = Modifier
                    .fillMaxWidth(),
                localTime = "13:12",
                windSpeed = 24.5,
                airPressure = 35,
                humidity = 354
            )
        }
    }

@Composable
fun WeatherLocation(
    modifier: Modifier,
    city: String,
    isExpanded: Boolean
){
    Box(
        modifier = modifier
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
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
        }
    }
}

@Composable
fun Temperature(
    temperature: Int
) {
   Box(
       modifier = Modifier
           .height(320.dp)
           .width(320.dp)
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
               fontWeight = FontWeight.Medium,
               color = Color.Black,
           )
           Text(
               text = "$temperature°C",
               fontSize = 70.sp,
               fontWeight = FontWeight.Medium,
               color = Color.Black,
           )
       }
   }
}

@Composable
fun WeatherDetails(
    modifier: Modifier,
    localTime: String,
    windSpeed: Double,
    airPressure: Int,
    humidity: Int
) {
    Box(
        modifier = modifier
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

@Composable
fun ShowBlock(
    title: String,
    subtitle: String
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

@Preview
@Composable
fun WeatherLocationPreview() {
    WeatherTheme {
        WeatherLocation(
            modifier = Modifier,
            city = "test",
            isExpanded = false
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherDetailsPreview(){
    WeatherTheme {
        WeatherDetails(
            modifier = Modifier,
            localTime = "20:31",
            windSpeed = 1.4,
            airPressure = 731,
            humidity = 52
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropdown(
    modifier: Modifier = Modifier,
    selectedItem: String,
    items: List<String>,
    onSelect: (String) -> Unit
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        modifier = modifier,
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

@Preview(showBackground = true)
@Composable
private fun DropdownMenuExamplePreview() {
    WeatherTheme {
        run {
            val selectedItem = remember {
                mutableStateOf("Самара")
            }
            CityDropdown(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                selectedItem = selectedItem.value,
                items = listOf("Самара", "Москва", "Владивосток")
            ) { selectedItem.value = it }
        }
    }
}