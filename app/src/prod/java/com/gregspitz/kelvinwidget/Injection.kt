package com.gregspitz.kelvinwidget

import com.gregspitz.kelvinwidget.data.source.WeatherDataSource
import com.gregspitz.kelvinwidget.data.source.remote.RetrofitRemoteWeatherDataSource
import com.gregspitz.kelvinwidget.weather.domain.usecase.GetWeatherUseCase

/**
 * Dependency injection
 */
object Injection {

    fun provideUseCaseHandler() : UseCaseHandler {
        return UseCaseHandler.getInstance(UseCaseThreadPoolScheduler())
    }

    fun provideWeatherDataSource() : WeatherDataSource {
        return RetrofitRemoteWeatherDataSource
    }

    fun provideGetWeatherUseCase() : GetWeatherUseCase {
        return GetWeatherUseCase(provideWeatherDataSource())
    }
}
