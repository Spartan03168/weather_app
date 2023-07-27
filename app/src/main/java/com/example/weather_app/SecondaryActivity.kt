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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
//    // Initializing a volley request
//    val requestQueue = Volley.newRequestQueue(LocalContext.current)
//
//    //API Endpoint URL
//    val apiURL = "https://weatherapi-com.p.rapidapi.com/current.json?q=53.1%2C-0.13"
//
//    //Create API Request
//    val jsonObjectRequest = JsonObjectRequest(
//        Request.Method.GET, apiURL, null,
//        Response.Listener { response ->
//        val condition = response.getJSONObject("current").getString("condition")
//        val  temperature = response.getJSONObject("current").getString("temp_c")
//        val humidity = response.getJSONObject("current").getString("humidity")
//        val windSpeed = response.getJSONObject("current").getString("wind_kph")
//        },
//        Response.ErrorListener { error ->  }
//    )
//    requestQueue.add(jsonObjectRequest)

    // Sample forecast data for demonstration purposes
    var devMode = 1
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val ForecastData = mutableListOf<LocalDate>()
    val TemperatureData = mutableListOf<Int>()
    val context = LocalContext.current
    val weatherData = remember { mutableStateOf(WeatherData()) }

    // Call the API to fetch weather data
    fetchWeatherData(weatherData)
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
            text = "Conditions: ${weatherData.value.condition}",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Air quality: 1.3(SI)",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Temperature: ${weatherData.value.temperature}",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Humidity: ${FetchWeatherData(weatherData = weatherData.value.humidity)}",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Wind speed: ${weatherData.value.windSpeed}",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Wind direction: SW(SI)",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Rainfall proability: 41%(SI)",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Sunrise: 0638(SI)",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Sunset: 2116(SI)",
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
            Text(text = "Forecasting and Historical details")
        }
        // Call the ForecastPanel function passing the sample forecast data
        //ForecastPanel(forecastData = sampleForecastData)
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
fun historic_data(){
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    val lastDays = mutableListOf<LocalDate>()

    for (i in 1..7){
        val dateToDisplay = currentDate.minusDays(i.toLong())
        lastDays.add(dateToDisplay)
    }

    for (days in lastDays.reversed()){
        Text(
            text = "Date: ${days.format(dateFormatter)}",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun FetchWeatherData(weatherData: MutableState<WeatherData>) {
    val requestQueue = Volley.newRequestQueue(LocalContext.current)
    val apiURL = "https://weatherapi-com.p.rapidapi.com/current.json?q=53.1%2C-0.13"
    val apiKey = "95a088559cmsh02b67b89f71100fp143370jsnbe340908d363"

    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET, apiURL, null,
        { response ->
            val condition = response.getJSONObject("current").getString("condition")
            val temperature = response.getJSONObject("current").getInt("temp_c")
            val humidity = response.getJSONObject("current").getInt("humidity")
            val windSpeed = response.getJSONObject("current").getInt("wind_kph")

            // Update the weatherData state with the fetched data
            weatherData.value = WeatherData(condition, temperature, humidity, windSpeed)
        },
        { error -> }
    ){
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["X-RapidAPI-Key"] = apiKey
            headers["X-RapidAPI-Host"] = "weatherapi-com.p.rapidapi.com"
            return headers
        }
    }

    requestQueue.add(jsonObjectRequest)
}

data class WeatherData(
    val condition: String = "",
    val temperature: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Int = 0
)