package com.gregspitz.kelvinwidget.data.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit service builder
 */
object ServiceBuilder {

    private const val baseUrl = "https://api.openweathermap.org/"

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    private val builder = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    private val retrofit = builder.build()

    fun <S> buildService(serviceType: Class<S>) : S {
        return retrofit.create(serviceType)
    }

}
