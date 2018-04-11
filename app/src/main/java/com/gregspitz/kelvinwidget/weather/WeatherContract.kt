package com.gregspitz.kelvinwidget.weather

import com.gregspitz.kelvinwidget.BasePresenter
import com.gregspitz.kelvinwidget.BaseView
import com.gregspitz.kelvinwidget.data.model.WeatherData

/**
 * Contract between TemperatureView and its presenter
 */
interface WeatherContract {

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
