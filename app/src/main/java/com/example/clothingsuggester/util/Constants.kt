package com.example.clothingsuggester.util

object Constants {
    const val apiKey ="e48ea02d4f5c698ea225e4b41f03758a"

    fun getWeatherUrlIcon(iconName:String): String {
        return "https://openweathermap.org/img/wn/${iconName}@2x.png"
    }
    const val url ="api.openweathermap.org"
}