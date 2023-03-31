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
    ): Call<WeatherData>

    @GET("/v1/forecast")
    fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") current_weather: String = "true",
    ): Call<CurrentWeather>

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