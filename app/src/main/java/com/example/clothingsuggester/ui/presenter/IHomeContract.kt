package com.example.clothingsuggester.ui.presenter

import android.content.Context
import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import java.io.IOException

interface IHomeContract {
    interface View {
        fun connectWeatherData(weatherData: WeatherDataResponse)

        fun showSuggestionCloths(image: Int)
        fun onFailRequest(error: IOException)


    }

    interface Presenter {
        fun fetchData(weatherDataParams: WeatherDataParams)
        fun onSuccess(weatherDataResponse: WeatherDataResponse)

        fun onFailure(error: IOException)


        fun saveClothesAndCurrentDate(
            weatherData: WeatherDataResponse,
            context: Context,
        )

    }
}