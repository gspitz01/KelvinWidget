package com.gregspitz.kelvinwidget.data.source

import com.gregspitz.kelvinwidget.data.model.WeatherData

/**
 * interface for WeatherData sources
 */
interface WeatherDataSource {

    interface GetWeatherDataCallback {

        fun onWeatherLoaded(weather: WeatherData)

        fun onDataNotAvailable()
    }

    fun getWeatherData(callback: GetWeatherDataCallback)
}
