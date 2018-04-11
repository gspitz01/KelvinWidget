package com.gregspitz.kelvinwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.gregspitz.kelvinwidget.weather.WeatherActivity
import com.gregspitz.kelvinwidget.weather.domain.usecase.GetWeatherUseCase
import java.text.DateFormat
import java.util.*

/**
 * Widget to show current temperature in Kelvin
 */
class KelvinWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        updateWidget(context, appWidgetManager, appWidgetIds)

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int, temperature: Double, currentTime: String) {

            val intent = Intent(context, WeatherActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.kelvin_widget)
            val temperatureText = "${temperature}K"
            views.setTextViewText(R.id.appwidget_text, temperatureText)
            val updateText = "Updated: $currentTime"
            views.setTextViewText(R.id.update_date, updateText)
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        internal fun updateWidget(context: Context, appWidgetManager: AppWidgetManager,
                                  appWidgetIds: IntArray) {
            Injection.provideUseCaseHandler().execute(Injection.provideGetWeatherUseCase(),
                    GetWeatherUseCase.RequestValues(),
                    object: UseCase.UseCaseCallback<GetWeatherUseCase.ResponseValue> {
                        override fun onSuccess(response: GetWeatherUseCase.ResponseValue) {
                            val currentTime = DateFormat.getDateTimeInstance().format(Date())
                            for (appWidgetId in appWidgetIds) {
                                updateAppWidget(context, appWidgetManager, appWidgetId,
                                        response.weatherData.main.temp, currentTime)
                            }
                        }

                        override fun onError() {
                            // Ignore
                        }
                    })
        }

    }
}

