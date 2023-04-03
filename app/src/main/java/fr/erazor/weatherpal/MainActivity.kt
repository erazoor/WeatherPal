package fr.erazor.weatherpal

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import fr.erazor.weatherpal.viewmodel.CardViewModel
import fr.erazor.weatherpal.viewmodel.DataViewModel
import fr.erazor.weatherpal.viewmodel.TimeViewModel
import fr.erazor.weatherpal.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun changeBackground() {
            val background = findViewById<LinearLayout>(R.id.background)

            when (TimeViewModel().getTimeRange()) {
                1 -> background.setBackgroundResource(R.drawable.light)
                2 -> background.setBackgroundResource(R.drawable.crep)
                else -> background.setBackgroundResource(R.drawable.dark)
            }
        }

        fun changeWeatherStatus() {
            val weatherStatus: ImageView = findViewById(R.id.weatherStatus)
            val status = WeatherViewModel(this)
            status.getWeatherStatus()
            status.weatherStatus.observe(this) {
                when (it.toInt()) {
                    1 -> weatherStatus.setImageResource(R.drawable.sun)
                    2 -> weatherStatus.setImageResource(R.drawable.clouds)
                    3 -> weatherStatus.setImageResource(R.drawable.sun_clouds_rain)
                    4 -> weatherStatus.setImageResource(R.drawable.clouds_snow)
                    5 -> weatherStatus.setImageResource(R.drawable.lightning)
                }
            }
        }

        fun changeCity() {
            val city: String? = intent.getStringExtra("city")
            val loc: TextView = findViewById(R.id.location)

            loc.text = city
        }

        fun changeTemp() {
            val temperature: TextView = findViewById(R.id.temperature)
            val temp = WeatherViewModel(this)
            temp.getTemperature()
            temp.temperature.observe(this) {
                temperature.text = "$it °C"
            }
        }

        fun changeDate() {
            val dateText: TextView = findViewById(R.id.date)
            val date = WeatherViewModel(this)
            date.getDate()
            date.date.observe(this) {
                dateText.text = "$it"
            }
        }

        fun changeWindDirection() {
            val windDirectionText: TextView = findViewById(R.id.windDirection)
            val windDirection = WeatherViewModel(this)
            windDirection.getWindDirection()
            windDirection.windDirection.observe(this) {
                windDirectionText.text = "$it"
            }
        }

        fun changeWindSpeed() {
            val windSpeedText: TextView = findViewById(R.id.windSpeed)
            val windSpeed = WeatherViewModel(this)
            windSpeed.getWindSpeed()
            windSpeed.windSpeed.observe(this) {
                windSpeedText.text = "$it km/h"
            }
        }

        fun changeHumidity() {
            val humidityText: TextView = findViewById(R.id.humidityValue)
            val humidity = WeatherViewModel(this)
            humidity.getHumidity()
            humidity.humidity.observe(this) {
                humidityText.text = "$it %"
            }
        }

        changeWeatherStatus()
        changeWindSpeed()
        changeHumidity()
        changeWindDirection()
        changeDate()
        changeCity()
        changeBackground()
        changeTemp()
    }
}

