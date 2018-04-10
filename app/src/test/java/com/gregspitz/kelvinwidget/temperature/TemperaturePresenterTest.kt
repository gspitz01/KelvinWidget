package com.gregspitz.kelvinwidget.temperature

import com.gregspitz.kelvinwidget.TestData
import com.gregspitz.kelvinwidget.TestUseCaseScheduler
import com.gregspitz.kelvinwidget.UseCaseHandler
import com.gregspitz.kelvinwidget.data.model.*
import com.gregspitz.kelvinwidget.data.source.WeatherDataSource
import com.gregspitz.kelvinwidget.temperature.domain.usecase.GetWeatherUseCase
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Captor

/**
 * tests for the implementation of {@link TemperaturePresenter}
 */
class TemperaturePresenterTest {



    private val view: TemperatureContract.View = mock()

    private val dataSource: WeatherDataSource = mock()

    private lateinit var presenter: TemperaturePresenter

    @Captor
    private val getWeatherDataCaptor = argumentCaptor<WeatherDataSource.GetWeatherDataCallback>()

    @Before
    fun setup() {
        whenever(view.isActive()).thenReturn(true)
    }

    @Test
    fun onStart_loadsWeatherData() {
        presenter = createPresenter()
        verify(view).setPresenter(presenter)
        presenter.start()
        verify(dataSource).getWeatherData(getWeatherDataCaptor.capture())
        // Trigger callback
        getWeatherDataCaptor.firstValue.onWeatherLoaded(TestData.weatherData)
        verify(view).showWeather(TestData.weatherData)
    }

    private fun createPresenter(): TemperaturePresenter {
        val getWeatherUseCase = GetWeatherUseCase(dataSource)
        val useCaseHandler = UseCaseHandler(TestUseCaseScheduler())
        return TemperaturePresenter(useCaseHandler, view, getWeatherUseCase)
    }
}
