package com.gregspitz.kelvinwidget.data.model

/**
 * OpenWeatherMap sys data
 */
data class Sys(val type: Int, val id: Int, val message: Double, val country: String,
               val sunrise: Int, val sunset: Int)
