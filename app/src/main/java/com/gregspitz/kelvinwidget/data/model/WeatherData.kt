package com.gregspitz.kelvinwidget.data.model

/**
 * represents weather data response from OpenWeatherMap
 */
data class WeatherData(val coord: Coord, val sys: Sys, val weather: List<Weather>,
                       val base: String, val main: MainWeather, val wind: Wind,
                       val clouds: Clouds, val dt: Int, val id: Int,
                       val name: String, val cod: Int) {

    override fun toString(): String {
        val stringBuilder = StringBuilder()

        stringBuilder.append("Lon: ${coord.lon}\nLat: ${coord.lat}\n")
        for (weather in weather) {
            stringBuilder.append("${weather.description}\n")
        }
        stringBuilder.append("Temp: ${main.temp}K\n")
        stringBuilder.append("Humidity: ${main.humidity}%\n")
        stringBuilder.append("Pressure: ${main.pressure}\n")
        stringBuilder.append("Wind: ${wind.speed}\n")

        return stringBuilder.toString()
    }
}
