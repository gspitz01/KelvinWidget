package com.gregspitz.kelvinwidget.weather.domain.usecase

import com.gregspitz.kelvinwidget.UseCase
import com.gregspitz.kelvinwidget.data.model.WeatherData
import com.gregspitz.kelvinwidget.data.source.WeatherDataSource

/**
 * Use case for retrieving the weather
 */
class GetWeatherUseCase(private val weatherDataSource: WeatherDataSource)
    : UseCase<GetWeatherUseCase.RequestValues, GetWeatherUseCase.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        weatherDataSource.getWeatherData(object: WeatherDataSource.GetWeatherDataCallback {
            override fun onWeatherLoaded(weather: WeatherData) {
                getUseCaseCallback().onSuccess(ResponseValue(weather))
            }

            override fun onDataNotAvailable() {
                getUseCaseCallback().onError()
            }
        })
    }

    class RequestValues : UseCase.RequestValues

    class ResponseValue(val weatherData: WeatherData) : UseCase.ResponseValue
}
