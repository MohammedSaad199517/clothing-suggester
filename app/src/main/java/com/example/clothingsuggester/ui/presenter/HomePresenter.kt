package com.example.clothingsuggester.ui.presenter

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.clothingsuggester.data.apiManager.IWeatherApi
import com.example.clothingsuggester.data.apiManager.WeatherApiManager
import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import com.example.clothingsuggester.util.Clothes
import com.example.clothingsuggester.util.Constants
import com.example.clothingsuggester.util.SharedPreferenceUtil
import java.io.IOException
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HomePresenter(private val homeView: IHomeContract.View) : IHomeContract.Presenter {
    private val weatherApi: IWeatherApi = WeatherApiManager()

    override fun fetchData(weatherDataParams: WeatherDataParams) {
        weatherApi.getWeather(weatherDataParams, ::onSuccess, ::onFailure)
    }

    override fun onSuccess(weatherDataResponse: WeatherDataResponse) {
        homeView.connectWeatherData(weatherDataResponse)
    }

    override fun saveClothesAndCurrentDate(
        weatherData: WeatherDataResponse,
        context: Context,
    ) {
        val sharedPreference = SharedPreferenceUtil(context)
        val currentDate = LocalDate.now().toString()
        val dateFromSharedPrefer =
            SharedPreferenceUtil(context).getCurrentDate(Constants.CURRENT_DATE_KEY, "")
        val clothesIdFromSharedPrefer = sharedPreference.getClothesId(Constants.CLOTHES_KEY, 0)


        if (currentDate != dateFromSharedPrefer) {

            sharedPreference.saveCurrentDate(Constants.CURRENT_DATE_KEY, currentDate)
            //check if there is clothes id in sharedPreferences
            if (sharedPreference.checkContainData(Constants.CLOTHES_KEY)) {

                saveClothesId(
                    weatherData.main.temp,
                    sharedPreference,
                    clothesIdFromSharedPrefer
                )
                homeView.showSuggestionCloths(clothesIdFromSharedPrefer)

            }
        } else {
            homeView.showSuggestionCloths(clothesIdFromSharedPrefer)
        }
    }

    private fun saveClothesId(
        temperature: Double,
        sharedPreference: SharedPreferenceUtil,
        clothesIdFromSharedPrefer: Int
    ) {

        if (temperature > Constants.MID_TEMP.toInt()) {

            val clothesSummerTodayId =
                Clothes()
                    .listOfClothsSummer
                    .filter { it != clothesIdFromSharedPrefer }
                    .random()

            sharedPreference.saveClothesId(Constants.CLOTHES_KEY, clothesSummerTodayId)

        } else {

            val clothesWinterTodayId =
                Clothes()
                    .listOfClothsWinter
                    .filter { it != clothesIdFromSharedPrefer }
                    .random()

            sharedPreference.saveClothesId(Constants.CLOTHES_KEY, clothesWinterTodayId)
        }
    }

    override fun onFailure(error: IOException) {
        homeView.onFailRequest(error)

    }


}