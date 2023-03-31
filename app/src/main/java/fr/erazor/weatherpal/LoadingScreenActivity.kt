package fr.erazor.weatherpal

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import fr.erazor.weatherpal.viewmodel.DataViewModel

class LoadingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        val latitude: Double = DataViewModel(this).getGPSLocation(this)[0]
        val longitude: Double = DataViewModel(this).getGPSLocation(this)[1]
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(latitude, longitude, 1)

        val ivumbrella = findViewById<ImageView>(R.id.iv_umbrella)

        ivumbrella.alpha = 0f
        ivumbrella.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", list?.get(0)?.locality)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}