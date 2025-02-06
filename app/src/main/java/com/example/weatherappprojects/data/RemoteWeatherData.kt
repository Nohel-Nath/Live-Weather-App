package com.example.weatherappprojects.data

import android.health.connect.datatypes.units.Temperature
import com.google.gson.annotations.SerializedName

data class RemoteWeatherData(
    val current: CurrentWeatherRemote,
    val forecast: ForecastRemote
)

data class CurrentWeatherRemote(
    @SerializedName("temp_c") val temperature: Float,
    val condition: WeatherConditionRemote,
    @SerializedName("wind_kph") val wind: Float,
    val humidity: Int
)

data class ForecastRemote(
    @SerializedName("forecastday") val forcastDay: List<ForecastDayRemote>,
)

data class ForecastDayRemote(
    val day: DayRemote,
    val hour: List<ForecastHourRemote>,
    val astro: AstroRemote
)

data class DayRemote(
    @SerializedName("daily_chance_of_rain   ") val chanceOfRain: Int
)

data class ForecastHourRemote(
    val time: String,
    @SerializedName("temp_c") val temperature: Float,
    @SerializedName("feelslike_c") val feelsLikeTemperature: Float,
    val condition: WeatherConditionRemote
)

data class AstroRemote(
    @SerializedName("sunrise") val sunriseTime: String,
    @SerializedName("sunset") val sunsetTime: String,
)

data class WeatherConditionRemote(
    val icon: String
)
