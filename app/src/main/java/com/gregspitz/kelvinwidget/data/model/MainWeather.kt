package com.gregspitz.kelvinwidget.data.model

/**
 * OpenWeatherMap main weather data
 */
data class MainWeather(val temp: Double, val humidity: Int, val pressure: Double,
                       val tempMin: Double, val tempMax: Double)
