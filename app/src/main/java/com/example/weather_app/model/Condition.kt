package com.example.weather_app.model

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val code: Int = 0,
    val icon: String = "",
    val text: String = ""
)