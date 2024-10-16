//package com.example.weather
//
//import android.icu.text.CaseMap.Title
//import android.os.Bundle
//import android.telecom.Call.Details
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.weather.ui.theme.WeatherTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WeatherTheme {
//            }
//        }
//    }
//}
//
//@Composable
//fun WeatherDetails(
//    localTime: String,
//    windSpeed: Double,
//    airPressure: Int,
//    humidity: Int
//) {
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .padding(
//            horizontal = 24.dp,
//            vertical = 44.dp
//        )
//        .background(
//            color = Color.Black.copy(0.05f),
//            shape = RoundedCornerShape(12.dp)
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    horizontal = 15.dp,
//                    vertical = 6.dp
//                ),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            DetailsTextBlock(
//                title = "Время",
//                subtitle = localTime
//            )
//        }
//    }
//}
//
//    @Composable
//    fun DetailsTextBlock(
//        title: String,
//        subtitle: String
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(4.dp)
//        ) {
//
//        }
//    }
//
//@Composable
//fun Weather(
//    city: String,
//    temperature: Int,
//    localTime: String,
//    windSpeed: Double,
//    airPressure: Int,
//    humidity:String
//) {
//    Scaffold(
//        topBar = {
//            Row(
//                modifier = Modifier
//                    .padding(20.dp)
//                    .background(color = Color.Blue)
//                    .height(40.dp)
//                    .width(327.dp)
//
//            ) {
//Text(
//    modifier = Modifier,
//    fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium))),
//    text = "text"
//
//)
//            }
//
//        },
//        bottomBar = {
//         Row {
//             modifier = Modifier
//                 .padding()
//
//
//         }
//        }
//    ) {
//
//    }
//}