package fr.erazor.weatherpal.viewmodel

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import java.io.IOException
import java.util.*

class DataViewModel(context: Context): ViewModel() ,LocationListener {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val geocoder = Geocoder(context, Locale.FRANCE)
    private val locationPermissionCode = 2
    var latitude: Double = 54.837789
    var longitude: Double = -0.57918

    init {
        getGPSLocation(context)
    }

    fun getGPSLocation(context: Context): List<Double> {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
        return listOf(latitude, longitude)
    }

    fun getCityName(latitude: Double, longitude: Double): String {
        return try {
            val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses!!.size > 0) {
                addresses[0].locality
            } else {
                "Unknown city"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }

    /*fun getCity(latitude: Double, longitude:Double) {
        val gcd = Geocoder(this, Locale.FRANCE)
        val addresses: MutableList<Address>? = gcd.getFromLocation(latitude, longitude, 1)
        if (addresses!!.size > 0) {
            println(addresses!![0].getLocality())
            city = addresses
                //addresses!![0].getLocality()
        } else {
        }
    }*/

    fun getTemperature(): String {

        return "°C"
    }

    override fun onLocationChanged(p0: Location) {
        latitude = p0.latitude
        longitude = p0.longitude
    }
}