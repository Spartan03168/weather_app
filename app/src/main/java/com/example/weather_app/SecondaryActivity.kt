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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weather_app.model.WeatherData
import java.time.LocalDate
import kotlin.random.Random


class SecondaryActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Weather_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
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
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val context = LocalContext.current

    // Fetch weather data using WeatherDataRepository
    val weatherData = remember { mutableStateOf(WeatherData()) }
    LaunchedEffect(Unit) {
        weatherData.value = WeatherDataRepository.fetchWeatherData()
    }

    Spacer(modifier = Modifier.height(12.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.icon_images),
            contentDescription = "The images of the possible icons availible."
        )

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
            text = "Conditions: ${weatherData.value.current.condition}",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Temperature: ${weatherData.value.current.temp_c}°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Humidity: ${weatherData.value.current.humidity}%",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Wind speed: ${weatherData.value.current.wind_kph} km/h",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        // Other weather data fields can be added here
        // ...

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context, SecondaryActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Forecasting and Historical details")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastPanel(forecastData: List<ForecastItem>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Forecast",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Call the data_viewer function passing the forecastData list
        data_viewer(forecastData = forecastData)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Historic data",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        historic_data()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun data_viewer(forecastData: List<ForecastItem>) {
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val nextThreeDays = (1..3).map{currentDate.plusDays(it.toLong())}

    nextThreeDays.forEachIndexed { index, date ->
        val forecastItem = forecastData.getOrNull(index)
        val temperatureText = forecastItem?.let { "Temperature: ${it.temperature}°C" } ?: "N/A"
        Text(
            text = "Date: ${date.format(dateFormatter)}, $temperatureText",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun historic_data() {
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val lastDays = mutableListOf<LocalDate>()

    // Generate the last 5 days (excluding the current date)
    for (i in 1..5) {
        val dateToDisplay = currentDate.minusDays(i.toLong())
        lastDays.add(dateToDisplay)
    }

    // Create a list of historic data with random temperature values between 20 and 35 degrees Celsius
    val historicData = lastDays.map { HistoricItem(dateFormatter.format(it), Random.nextInt(20, 35)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Call the historic_viewer function passing the historicData list
        historic_viewer(historicData = historicData)
    }
}

data class HistoricItem(val date: String, val temperature: Int)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun historic_viewer(historicData: List<HistoricItem>) {
    historicData.forEach { historicItem ->
        Text(
            text = "Date: ${historicItem.date}, Temperature: ${historicItem.temperature}°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}