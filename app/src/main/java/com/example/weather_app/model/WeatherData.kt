package com.example.weather_app.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val current: Current = Current(),
    val location: Location = Location()
)