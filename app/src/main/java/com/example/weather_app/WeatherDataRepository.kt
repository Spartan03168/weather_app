import com.example.weather_app.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.features.get
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
//
@Serializable
data class WeatherResponse(val current: CurrentData)

@Serializable
data class CurrentData(
    val condition: String,
    val temp_c: Int,
    val humidity: Int,
    val wind_kph: Int
)

object WeatherDataRepository {
    private val apiKey = "95a088559cmsh02b67b89f71100fp143370jsnbe340908d363" // Replace this with your actual API key
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchWeatherData(): WeatherData {
        val apiUrl = "https://weatherapi-com.p.rapidapi.com/current.json"

        val response: WeatherResponse = withContext(Dispatchers.IO) {
            client.get {
                url {
                    takeFrom(apiUrl)
                }
                method = HttpMethod.Get
                header("X-RapidAPI-Key", apiKey)
                header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                parameter("q", "53.1,-0.13")
            }
        }

        return WeatherData(
            condition = response.current.condition,
            temperature = response.current.temp_c,
            humidity = response.current.humidity,
            windSpeed = response.current.wind_kph
        )
    }
}