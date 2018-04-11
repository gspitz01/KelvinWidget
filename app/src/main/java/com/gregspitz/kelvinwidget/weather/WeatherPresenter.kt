package com.gregspitz.kelvinwidget.weather

import com.gregspitz.kelvinwidget.UseCase
import com.gregspitz.kelvinwidget.UseCaseHandler
import com.gregspitz.kelvinwidget.weather.domain.usecase.GetWeatherUseCase

/**
 * presenter for TemperatureView
 */
class WeatherPresenter(private val useCaseHandler: UseCaseHandler,
                       private val view: WeatherContract.View,
                       private val getWeatherUseCase: GetWeatherUseCase)
    : WeatherContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadTemperature()
    }

    override fun loadTemperature() {
        useCaseHandler.execute(getWeatherUseCase, GetWeatherUseCase.RequestValues(),
                object: UseCase.UseCaseCallback<GetWeatherUseCase.ResponseValue> {
                    override fun onSuccess(response: GetWeatherUseCase.ResponseValue) {
                        if (view.isActive()) {
                            view.showWeather(response.weatherData)
                        }
                    }

                    override fun onError() {
                        if (view.isActive()) {
                            view.showFailedToLoadWeather()
                        }
                    }
                })
    }
}
