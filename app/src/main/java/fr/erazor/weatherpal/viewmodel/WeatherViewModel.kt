package fr.erazor.weatherpal.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.erazor.weatherpal.model.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherViewModel(context: Context) : ViewModel() {
    private val context = context
    private val _temperature = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()
    private val _windDirection = MutableLiveData<String>()
    private val _humidity = MutableLiveData<String>()
    private val _windSpeed = MutableLiveData<String>()
    private val _weatherStatus = MutableLiveData<String>()

    val temperature: LiveData<String>
        get() = _temperature

    val date: LiveData<String>
        get() = _date

    val windDirection: LiveData<String>
        get() = _windDirection

    val humidity: LiveData<String>
        get() = _humidity

    val windSpeed: LiveData<String>
        get() = _windSpeed

    val weatherStatus: LiveData<String>
        get() = _weatherStatus


    private val location = DataViewModel(context).getGPSLocation(context)
    private val currentWeather = WeatherAPI.create().getCurrentWeather(location[0], location[1])
    private val hourlyData = WeatherAPI.create().getWeather(location[0], location[1])
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getTemperature() {
        scope.launch {
            val temperature = currentWeather.execute().body()?.currentWeather?.temperature
            _temperature.postValue(temperature.toString())
        }
    }

    fun getDate() {
        scope.launch {
            val dateTime = currentWeather.execute().body()?.currentWeather?.time
            val dateRegex = Regex("^(\\d{4})-(\\d{2})-(\\d{2})")
            val dateMatch = dateRegex.find(dateTime ?: "")
            val year = dateMatch?.groupValues?.get(1) ?: ""
            val month = dateMatch?.groupValues?.get(2) ?: ""
            val day = dateMatch?.groupValues?.get(3) ?: ""
            val date = "$day/$month/$year"
            _date.postValue(date)
        }
    }

    fun getWindDirection() {
        scope.launch {
            val windDirectionDegrees = currentWeather.execute().body()?.currentWeather?.windDirection ?: 0.0
            val windDirections = arrayOf("North", "North East", "East", "South East", "South", "South West", "West", "North West", "North")
            val windDirectionIndex = (windDirectionDegrees / 45.0).roundToInt() % 8
            val windDirection = windDirections[windDirectionIndex]
            _windDirection.postValue(windDirection)
        }
    }

    fun getWindSpeed() {
        scope.launch {
            val windSpeed = currentWeather.execute().body()?.currentWeather?.windSpeed ?: 0.0
            _windSpeed.postValue(windSpeed.toString())
        }
    }

    fun getHumidity() {
        scope.launch {
            val humidity = hourlyData.execute().body()?.hourly?.relativeHumidity?.get(0) ?: 0.0
            _humidity.postValue(humidity.toString())
        }
    }

    fun getWeatherStatus() {
        scope.launch {
            val weatherStatus = when (hourlyData.execute().body()?.hourly?.weatherCode?.get(0) ?: 0) {
                0 -> 1 // "Ensoleillé"
                1, 2, 3 -> 2 // "Nuageux"
                45, 48 -> 2 // "Brouillard"
                51, 53, 55 -> 2 // "Bruine"
                56, 57 -> 2 // "Bruine verglaçante"
                61, 63, 65 -> 3 // "Pluvieux"
                66, 67 -> 3 // "Pluie verglaçante"
                71, 73, 75 -> 4 // "Neige"
                77 -> 4 // "Grains de neige"
                80, 81, 82 -> 3 // "Averses de pluie"
                85, 86 -> 4 // "Averses de neige"
                95 -> 5 // "Orage"
                96, 99 -> 5 // "Orage avec grêle"
                else -> 1 // "Temps inconnu"
            }
            _weatherStatus.postValue(weatherStatus.toString())
        }
    }
}
