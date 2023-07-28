import com.example.weather_app.model.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WeatherDataRepository {
    val city = "Barcelona"
    val apiKey = "95a088559cmsh02b67b89f71100fp143370jsnbe340908d363"
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchWeatherData(): WeatherData {
        val apiUrl = "https://weatherapi-com.p.rapidapi.com/current.json"
        val response: WeatherData = withContext(Dispatchers.IO) {
            client.get {
                url {
                    takeFrom(apiUrl)
                }
                method = HttpMethod.Get
                header("X-RapidAPI-Key", apiKey)
                header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                parameter("q", city)
            }
        }

        return response
    }
}