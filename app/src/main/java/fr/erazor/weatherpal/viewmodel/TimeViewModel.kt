package fr.erazor.weatherpal.viewmodel

class TimeViewModel {
    private fun getHour(): Int {
        val currentTime = System.currentTimeMillis()
        val hour = (currentTime / 1000 / 60 / 60) % 24
        return hour.toInt()
    }

    fun getTimeRange(): Int {
        val currentTime = getHour()
        val startDay = 9
        val endDay = 17
        val endAfternoon = 21

        return when (currentTime) {
            in startDay..endDay -> 1
            in endDay..endAfternoon -> 2
            else -> 3
        }
    }

    fun swapWeatherStatus() {
        // Create a function that fetch for the weather status (cloudy, rainy ...)
        // and swaps the image having the id "weatherStatus" for the one that refers to the good weather
    }
}