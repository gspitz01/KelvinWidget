package com.gregspitz.kelvinwidget.temperature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gregspitz.kelvinwidget.Injection
import com.gregspitz.kelvinwidget.R
import com.gregspitz.kelvinwidget.data.model.WeatherData
import kotlinx.android.synthetic.main.activity_temperature.*

class TemperatureActivity : AppCompatActivity(), TemperatureContract.View {

    private lateinit var presenter: TemperatureContract.Presenter

    private var active = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)

        TemperaturePresenter(Injection.provideUseCaseHandler(), this,
                Injection.provideGetWeatherUseCase())
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        active = true
    }

    override fun onPause() {
        super.onPause()
        active = false
    }

    override fun setPresenter(presenter: TemperatureContract.Presenter) {
        this.presenter = presenter
    }

    override fun showTemperature(temperature: Double) {
        weatherView.text = temperature.toString()
    }

    override fun showWeather(weather: WeatherData) {
        weatherView.text = weather.toString()
    }

    override fun showFailedToLoadWeather() {
        weatherView.setText(R.string.failed_to_load_weather_text)
    }

    override fun isActive(): Boolean {
        return active
    }
}
