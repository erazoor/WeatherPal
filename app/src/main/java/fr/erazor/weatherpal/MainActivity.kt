package fr.erazor.weatherpal

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import fr.erazor.weatherpal.viewmodel.DataViewModel
import fr.erazor.weatherpal.viewmodel.TimeViewModel
import fr.erazor.weatherpal.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun changeCity() {
            val city: String? = intent.getStringExtra("city")
            val loc: TextView = findViewById(R.id.location)

            loc.text = city
        }

        fun changeBackground() {
            val background = findViewById<LinearLayout>(R.id.background)

            when (TimeViewModel().getTimeRange()) {
                1 -> background.setBackgroundResource(R.drawable.light)
                2 -> background.setBackgroundResource(R.drawable.crep)
                else -> background.setBackgroundResource(R.drawable.dark)
            }
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
                println("Date : $it")
            }
        }

        changeDate()
        changeCity()
        changeBackground()
        changeTemp()
    }
}

