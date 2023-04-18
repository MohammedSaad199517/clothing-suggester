package com.example.clothingsuggester.util

object Constants {
    const val API_KEY ="e48ea02d4f5c698ea225e4b41f03758a"
    fun getWeatherUrlIcon(iconName:String) = "https://openweathermap.org/img/wn/${iconName}@2x.png"

    const val URL ="api.openweathermap.org"

    const val SHARED_PREFS_NAME ="clothing_suggester"
    const val CLOTHES_KEY ="clothes"
    const val CURRENT_DATE_KEY = "current_date"
    const val MID_TEMP ="18"


}