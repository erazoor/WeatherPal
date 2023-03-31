package fr.erazor.weatherpal.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timeZoneAbbreviation: String,
    val current_weather: CurrentWeatherData,
    val hourly: HourlyData,
)

data class HourlyData(
    @SerializedName("time") val timestamp: List<String>,
    @SerializedName("temperature_2m") val temperature: List<Float>,
    @SerializedName("precipitation_probability") val precipitationProbability: List<Int>,
    @SerializedName("weathercode") val weatherCode: List<Int>,
    @SerializedName("relativehumidity_2m") val relativeHumidity: List<Int>,
)

data class CurrentWeather(
    @SerializedName("current_weather") val currentWeather: CurrentWeatherData
)

data class CurrentWeatherData(
    @SerializedName("temperature") val temperature: Float,
    @SerializedName("windspeed") val windSpeed: Float,
    @SerializedName("winddirection") val windDirection: Double,
    @SerializedName("time") val time: String,
    )

data class WeatherDescription(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)
