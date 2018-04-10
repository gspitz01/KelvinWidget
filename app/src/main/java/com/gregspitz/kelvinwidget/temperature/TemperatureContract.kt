package com.gregspitz.kelvinwidget.temperature

import com.gregspitz.kelvinwidget.BasePresenter
import com.gregspitz.kelvinwidget.BaseView
import com.gregspitz.kelvinwidget.data.model.WeatherData

/**
 * Contract between TemperatureView and its presenter
 */
interface TemperatureContract {

    interface View : BaseView<Presenter> {

        fun showTemperature(temperature: Double)

        fun showWeather(weather: WeatherData)

        fun showFailedToLoadWeather()

        fun isActive() : Boolean
    }

    interface Presenter : BasePresenter {
        fun loadTemperature()
    }
}
