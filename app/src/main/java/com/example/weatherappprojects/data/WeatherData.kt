package com.example.weatherappprojects.data

import android.health.connect.datatypes.units.Temperature
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class WeatherData

data class CurrentLocation(
    val date: String = getCurrentData(),
    val Location: String = "Choose Your Location",
    val latitude: Double? = null,
    val longitude: Double? = null
) : WeatherData()

data class CurrentWeather(
    val icon: String,
    val temperature: Float,
    val wind: Float,
    val humidity: Int,
    val chaneOfRain: Int
) : WeatherData()

data class Forecast(
    val time: String,
    val temperature: Float,
    val feelsLikeTemperature: Float,
    val icon: String
) : WeatherData()

data class AstroData(
    val sunrise: String,
    val sunset: String
) : WeatherData()

private fun getCurrentData(): String {
    val currentDate = Date()
    val formatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return "Today ${formatter.format(currentDate)}"
}