package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.clothingsuggester.R
import com.example.clothingsuggester.data.apiManager.WeatherApiManager
import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import com.example.clothingsuggester.util.Constants
import com.example.clothingsuggester.databinding.ActivityHomeBinding
import com.example.clothingsuggester.ui.presenter.HomePresenter
import com.example.clothingsuggester.ui.presenter.IHomeContract
import com.example.clothingsuggester.util.loadImage
import kotlin.math.ceil

class HomeActivity : AppCompatActivity(), IHomeContract.View {

    lateinit var binding: ActivityHomeBinding
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBack()
    }
    private fun addCallBack(){

        presenter= HomePresenter(this)
        presenter.fetchData(WeatherDataParams("baghdad",Constants.apiKey))

    }

    override fun connectWeatherData(weatherData: WeatherDataResponse) {
        runOnUiThread {
            binding.weatherIcon.loadImage(Constants.getWeatherUrlIcon(weatherData.weather[0].icon))

            binding.temperature.text =  ceil( weatherData.main.temp).toInt().toString()+"\u00B0"

        }

    }

    override fun showSuggestionCloths(image: Int) {
        runOnUiThread {
            binding.imageViewSuggestion.setBackgroundResource( image)

        }
    }

}