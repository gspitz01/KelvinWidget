package com.gregspitz.kelvinwidget.weather

import com.gregspitz.kelvinwidget.TestData
import com.gregspitz.kelvinwidget.TestUseCaseScheduler
import com.gregspitz.kelvinwidget.UseCaseHandler
import com.gregspitz.kelvinwidget.data.source.WeatherDataSource
import com.gregspitz.kelvinwidget.weather.domain.usecase.GetWeatherUseCase
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Captor

/**
 * tests for the implementation of {@link WeatherPresenter}
 */
class WeatherPresenterTest {



    private val view: WeatherContract.View = mock()

    private val dataSource: WeatherDataSource = mock()

    private lateinit var presenter: WeatherPresenter

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

    private fun createPresenter(): WeatherPresenter {
        val getWeatherUseCase = GetWeatherUseCase(dataSource)
        val useCaseHandler = UseCaseHandler(TestUseCaseScheduler())
        return WeatherPresenter(useCaseHandler, view, getWeatherUseCase)
    }
}
