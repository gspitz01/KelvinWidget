package com.gregspitz.kelvinwidget.data.model

/**
 * represents weather data response from OpenWeatherMap
 */
data class WeatherData(val coord: Coord, val sys: Sys, val weather: List<Weather>,
                       val base: String, val main: MainWeather, val wind: Wind,
                       val clouds: Clouds, val dt: Int, val id: Int,
                       val name: String, val cod: Int)
