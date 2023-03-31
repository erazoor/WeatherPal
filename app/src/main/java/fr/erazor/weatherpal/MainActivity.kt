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

        val city: String? = intent.getStringExtra("city")
        println("$city $this")

        val loc: TextView = findViewById(R.id.location)
        loc.text = city

    }
}

