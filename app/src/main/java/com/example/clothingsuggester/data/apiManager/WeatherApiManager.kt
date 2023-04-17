package com.example.clothingsuggester.data.apiManager

import com.example.clothingsuggester.data.requests.WeatherDataParams
import com.example.clothingsuggester.data.responses.WeatherDataResponse
import com.example.clothingsuggester.ui.presenter.HomePresenter
import com.example.clothingsuggester.util.Constants
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


class WeatherApiManager : IWeatherApi {
    private val client = OkHttpClient()
    private val gson: Gson = Gson()

    override fun getWeather(
        params: WeatherDataParams,
        onSuccess: (WeatherDataResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {

        val url = HttpUrl.Builder()
            .scheme("https")
            .host(Constants.url)
            .addPathSegment("data")
            .addPathSegment("2.5")
            .addPathSegment("weather")
            .addQueryParameter("q", params.q)
            .addQueryParameter("units", "metric")
            .addQueryParameter("appid", params.appid)
            .build()
        val request = Request.Builder()
            .url(url).get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val result = gson.fromJson(jsonString, WeatherDataResponse::class.java)

                    onSuccess(result)
                }
            }
        })
    }
}
