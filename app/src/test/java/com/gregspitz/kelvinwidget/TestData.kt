package com.gregspitz.kelvinwidget

import com.gregspitz.kelvinwidget.data.model.*

/**
 * Data for testing
 */
object TestData {

    private val coord = Coord(104.33, 11.03)

    private val sys = Sys(14, 156, 14.5, "USA", 134234, 2342341)

    private val weather = listOf(Weather(1234, "Sunny", "Sunny", "iDunno"))

    private val mainWeather = MainWeather(310.15, 78, 14.4,
            295.5, 315.1)

    private val wind = Wind(34.4, 16.3)

    private val clouds = Clouds(2134)

    val weatherData = WeatherData(coord, sys, weather, "SomeString", mainWeather,
            wind, clouds, 123, 134, "Kevin", 1234)
}
