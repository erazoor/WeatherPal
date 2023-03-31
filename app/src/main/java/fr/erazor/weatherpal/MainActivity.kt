package fr.erazor.weatherpal

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.erazor.weatherpal.viewmodel.DataViewModel
import fr.erazor.weatherpal.viewmodel.TimeViewModel
import fr.erazor.weatherpal.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp: TextView = findViewById(R.id.temperature)
        temp.text = "°C"

        val city: String? = intent.getStringExtra("city")
        val loc: TextView = findViewById(R.id.location)
        loc.text = city

        val time = TimeViewModel().getTimeRange()
        val background = findViewById<LinearLayout>(R.id.background)

        when (time) {
            1 -> background.setBackgroundResource(R.drawable.light)
            2 -> background.setBackgroundResource(R.drawable.crep)
            else -> background.setBackgroundResource(R.drawable.dark)
        }

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // observe the temperature LiveData object
        weatherViewModel.temperature.observe(this, Observer {
            temp.text = it
        })

        // call the getWeather() function to fetch the temperature
        weatherViewModel.getWeather()
    }
}

