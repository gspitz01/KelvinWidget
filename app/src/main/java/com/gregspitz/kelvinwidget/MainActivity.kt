package com.gregspitz.kelvinwidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gregspitz.kelvinwidget.temperature.TemperatureActivity
import com.gregspitz.kelvinwidget.temperature.domain.usecase.GetWeatherUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureButton.setOnClickListener({
            startActivity(Intent(this@MainActivity, TemperatureActivity::class.java))
        })

        updateWidgetButton.setOnClickListener({
            val appWidgetManager = AppWidgetManager.getInstance(this@MainActivity)
            val appWidgetComponentName =
                    ComponentName(this@MainActivity, KelvinWidget::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(appWidgetComponentName)
            KelvinWidget.updateWidget(this@MainActivity, appWidgetManager, appWidgetIds)
        })
    }
}
