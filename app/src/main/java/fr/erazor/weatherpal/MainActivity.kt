package fr.erazor.weatherpal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.erazor.weatherpal.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp: TextView = findViewById(R.id.temperature)
        temp.text = "°C"

        val dataViewModel = DataViewModel(this)
        val userLat = dataViewModel.getGPSLocation(this)[0]
        val userLong = dataViewModel.getGPSLocation(this)[1]
        val city = dataViewModel.getCityName(userLat, userLong)
        println("$city $userLat $userLong")

        val loc: TextView = findViewById(R.id.location)
        loc.text = city

    }
}

