package com.gregspitz.kelvinwidget.temperature

import com.gregspitz.kelvinwidget.UseCase
import com.gregspitz.kelvinwidget.UseCaseHandler
import com.gregspitz.kelvinwidget.temperature.domain.usecase.GetWeatherUseCase

/**
 * presenter for TemperatureView
 */
class TemperaturePresenter(private val useCaseHandler: UseCaseHandler,
                           private val view: TemperatureContract.View,
                           private val getWeatherUseCase: GetWeatherUseCase)
    : TemperatureContract.Presenter {

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
