package fr.erazor.weatherpal.viewmodel

import android.icu.util.Calendar

class TimeViewModel {
    fun getTime(): String {
        val time = System.currentTimeMillis()
        val hour = time / 3600000
        val minute = time / 60000
        val second = time / 1000
        return "$hour:$minute:$second"
    }

    fun getTimeRange(): Int {
        val currentTime = getTime()
        val startDay = 9
        val endDay = 17
        val endAfternoon = 21
        val currentHour = currentTime[Calendar.HOUR_OF_DAY]
        // if the current time is between 9:OO and 18:00 return 1
        return when (currentHour) {
            in startDay..endDay -> {
                1
            }
            in endDay..endAfternoon -> {
                2
            }
            else -> {
                3
            }
        }
    }
}