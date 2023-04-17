package com.example.clothingsuggester.ui.presenter

import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import java.io.IOException

interface IHomeContract {
    interface View {
        fun connectWeatherData(weatherData: WeatherDataResponse)

        fun showSuggestionCloths(image: Int)

    }

    interface Presenter {
        fun fetchData( weatherDataParams: WeatherDataParams)
        fun onSuccess(weatherDataResponse: WeatherDataResponse)

        fun onFailure(e: IOException)

        fun showSuggestion(temp:Double)

    }
}