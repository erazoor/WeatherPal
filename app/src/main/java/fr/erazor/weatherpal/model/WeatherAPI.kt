package fr.erazor.weatherpal.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/v1/forecast")
    fun getCurrentLocation(
        @Query("latitude") latitude: Int,
        @Query("longitude") longitude: Int,
    ): Call<Weather>

    @GET("/v1/forecast")
    fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") current_weather: String = "true",
    ): Call<CurrentWeather>

    @GET("/v1/forecast")
    fun getWeather(
        @Query("longitude") longitude: Double = -0.57,
        @Query("latitude") latitude: Double = 44.86,
        @Query("timezone") timezone: String = "Europe/Berlin",
        @Query("current_weather") current_weather: String = "true",
        @Query("hourly") hourly: String = "temperature_2m,precipitation_probability,weathercode,relativehumidity_2m",
        @Query("daily") daily: String = "weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min"
    ): Call<Weather>

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/"

        fun create(): WeatherAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherAPI::class.java)
        }
    }
}