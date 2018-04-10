package com.gregspitz.kelvinwidget.data.source.remote

import com.gregspitz.kelvinwidget.SecretData
import com.gregspitz.kelvinwidget.data.model.WeatherData
import com.gregspitz.kelvinwidget.data.service.ServiceBuilder
import com.gregspitz.kelvinwidget.data.service.WeatherService
import com.gregspitz.kelvinwidget.data.source.WeatherDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit data source for OpenWeatherMap
 */
object RetrofitRemoteWeatherDataSource : WeatherDataSource {

    private val weatherService = ServiceBuilder.buildService(WeatherService::class.java)

    override fun getWeatherData(callback: WeatherDataSource.GetWeatherDataCallback) {
        val call = weatherService.getWeather("02184", SecretData.openWeatherMapApiKey)
        call.enqueue(object: Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                if (response?.body() == null) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onWeatherLoaded(response.body()!!)
                }
            }
        })
    }
}
