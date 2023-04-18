package com.example.clothingsuggester.ui


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import com.example.clothingsuggester.util.Constants
import com.example.clothingsuggester.databinding.ActivityHomeBinding
import com.example.clothingsuggester.ui.presenter.HomePresenter
import com.example.clothingsuggester.ui.presenter.IHomeContract
import com.example.clothingsuggester.util.SharedPreferenceUtil
import com.example.clothingsuggester.util.loadImage
import java.io.IOException
import java.time.LocalDate
import kotlin.math.ceil

@RequiresApi(Build.VERSION_CODES.O)
class HomeActivity : AppCompatActivity(), IHomeContract.View {

    lateinit var binding: ActivityHomeBinding
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBack()

    }

    private fun addCallBack() {


        presenter = HomePresenter(this)
        presenter.fetchData(WeatherDataParams("baghdad", Constants.API_KEY))


    }

    override fun connectWeatherData(weatherData: WeatherDataResponse) {
        runOnUiThread {

            binding.weatherIcon.loadImage(Constants.getWeatherUrlIcon(weatherData.weather[0].icon))

            binding.temperature.text = ceil(weatherData.main.temp).toInt().toString() + "\u00B0"
            presenter.saveClothesAndCurrentDate(weatherData, this)

        }

    }

    override fun showSuggestionCloths(image: Int) {
        runOnUiThread {
            binding.imageViewSuggestion.setBackgroundResource(image)

        }
    }

    override fun onFailRequest(error: IOException) {

        runOnUiThread {
            error.localizedMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

    }


}