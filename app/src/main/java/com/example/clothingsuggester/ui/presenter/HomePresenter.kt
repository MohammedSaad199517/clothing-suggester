package com.example.clothingsuggester.ui.presenter

import com.example.clothingsuggester.R
import com.example.clothingsuggester.data.apiManager.IWeatherApi
import com.example.clothingsuggester.data.apiManager.WeatherApiManager
import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import java.io.IOException


class HomePresenter(private val homeView: IHomeContract.View) : IHomeContract.Presenter {
    private val weatherApi :IWeatherApi = WeatherApiManager()
    override fun fetchData(weatherDataParams: WeatherDataParams) {
        weatherApi.getWeather(weatherDataParams,::onSuccess,::onFailure)
    }


    override fun onSuccess(weatherDataResponse: WeatherDataResponse) {
        homeView.connectWeatherData(weatherDataResponse)
        if(weatherDataResponse.main.temp>18){
            homeView.showSuggestionCloths(R.drawable.set_2)
        }
        else {
            homeView.showSuggestionCloths(R.drawable.set_1)
        }
    }

    override fun onFailure(e: IOException) {

    }

    override fun showSuggestion(temp: Double) {
    }


}