package fr.erazor.weatherpal

import fr.erazor.weatherpal.viewmodel.CoordinatesViewModel
import fr.erazor.weatherpal.viewmodel.WeatherViewModel

class MainActivity {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var coordinatesViewModel: CoordinatesViewModel

    fun onCreate() {
        weatherViewModel = WeatherViewModel()
        coordinatesViewModel = CoordinatesViewModel()
    }
}