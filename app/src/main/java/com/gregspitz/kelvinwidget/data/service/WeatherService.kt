package com.gregspitz.kelvinwidget.data.service

import com.gregspitz.kelvinwidget.data.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit service to use OpenWeatherMap API
 * api.openweathermap.org/data/2.5/weather
 */
interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeather(@Query("zip") zip: String,
                   @Query("APPID") id: String) : Call<WeatherData>
}
