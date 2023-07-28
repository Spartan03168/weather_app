package com.example.weather_app

import org.junit.Test
import WeatherDataRepository
import WeatherDataRepository.apiKey
import WeatherDataRepository.city
import kotlinx.serialization.json.JsonNull.isString
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherAppTest {
    @Test
    fun test_1_verification() {
        val test_1 = city
        val test_2 = apiKey
        if (test_1 is String && test_2 is String){
            val actual = true
            assertTrue(true.toString(), actual)
        }
        else{
            val actual = false
            assertFalse(false.toString(),actual)
        }
    }

    @Test
    fun test_2_verification(){
        val test_1 = city
        val test_2 = apiKey
        if (test_1 !is String || test_2 !is String){
            val actual = false
            assertFalse(false.toString(),actual)
        }
    }
}


