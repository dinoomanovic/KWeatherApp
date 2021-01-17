package com.odin.kweatherapp.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRestAdapter {
    private val baseUrl = "https://api.openweathermap.org"
    private val builder = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()
    fun createService(): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}