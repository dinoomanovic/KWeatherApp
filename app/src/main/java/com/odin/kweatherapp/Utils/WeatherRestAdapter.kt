package com.odin.kweatherapp.Utils

import okhttp3.OkHttpClient
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

class WeatherRestAdapter {
    private val BASE_URL = "http://api.openweathermap.org"
    private val builder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()
    fun createService(): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}