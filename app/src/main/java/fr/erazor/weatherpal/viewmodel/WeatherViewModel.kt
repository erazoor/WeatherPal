package fr.erazor.weatherpal.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.erazor.weatherpal.model.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(context: Context) : ViewModel() {
    private val context = context
    private val _temperature = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()

    val temperature: LiveData<String>
        get() = _temperature

    val date: LiveData<String>
        get() = _date

    fun getTemperature() {
        val location = DataViewModel(context).getGPSLocation(context)

        val currentWeather = WeatherAPI.create().getCurrentWeather(location[0], location[1])
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val temperature = currentWeather.execute().body()?.currentWeather?.temperature
            _temperature.postValue(temperature.toString())
        }
    }

    fun getDate() {
        val location = DataViewModel(context).getGPSLocation(context)

        val currentWeather = WeatherAPI.create().getCurrentWeather(location[0], location[1])
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val dateTime = currentWeather.execute().body()?.currentWeather?.time
            val dateRegex = Regex("^(\\d{4})-(\\d{2})-(\\d{2})")
            val dateMatch = dateRegex.find(dateTime ?: "")
            val year = dateMatch?.groupValues?.get(1) ?: ""
            val month = dateMatch?.groupValues?.get(2) ?: ""
            val day = dateMatch?.groupValues?.get(3) ?: ""
            val date = "$day/$month/$year"
            _date.postValue(date)
            println("Date : $date")
        }
    }
}
