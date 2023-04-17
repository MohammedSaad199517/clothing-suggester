package com.example.clothingsuggester.data.apiManager

import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import com.example.clothingsuggester.ui.presenter.HomePresenter
import java.io.IOException

interface IWeatherApi {
    fun getWeather(
        params:WeatherDataParams,
        onSuccess: (WeatherDataResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )
}