package com.example.weather_app

//Java imports
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.tiles.material.Text
import com.example.weather_app.ui.theme.Weather_appTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import java.time.LocalDate
import kotlin.random.Random

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Weather_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Text(text = "Secondary screen insert port")

                    val sampleForecastData = listOf(
                        ForecastItem("2023-06-25", 30),
                        ForecastItem("2023-06-26", 29),
                        ForecastItem("2023-06-27", 28),
                        // Add more forecast items as needed
                    )

                    ForecastPanel(sampleForecastData)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DefaultPreview() {
    Weather_appTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            weather_screen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun weather_screen() {
    // Sample forecast data for demonstration purposes
    var devMode = 1
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val ForecastData = mutableListOf<LocalDate>()
    val TemperatureData = mutableListOf<Int>()
    val context = LocalContext.current
    //Button(onClick = {
    //    val intent = Intent(context, MainActivity::class.java)
    //    context.startActivity(intent)
    //}) {
    //    Text(text = "Return")
    //}

    Spacer(modifier = Modifier.height(12.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(painter = painterResource(R.drawable.icon_images),
            contentDescription = "The images of the possible icons availible.")

        Text(
            text = "City: Barcelona",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = currentDate.format(dateFormatter),
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Today's weather",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Conditions: Sunny",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Temperature: 28°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Humidity: 85%",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Wind speed: 21 km/h",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Wind direction: SW",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Rainfall proability: 41%",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context,SecondaryActivity::class.java )
            context.startActivity(intent)
        }) {
            Text(text = "Further details")
        }
        // Call the ForecastPanel function passing the sample forecast data
        //ForecastPanel(forecastData = sampleForecastData)
    }
}

@Composable
fun ForecastPanel(forecastData: List<ForecastItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Forecast Panel",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Call the data_viewer function passing the forecastData list
        data_viewer(forecastData = forecastData)
    }
}

@Composable
fun data_viewer(forecastData: List<ForecastItem>) {
    forecastData.forEach { forecastItem ->
        Text(
            text = "Date: ${forecastItem.date}, Temperature: ${forecastItem.temperature}°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

