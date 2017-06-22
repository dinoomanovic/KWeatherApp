package com.odin.kweatherapp.Utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.odin.kweatherapp.Models.WeatherData

interface  WeatherApi {
    @GET("data/2.5/forecast")
    abstract fun getWeather(
            @Query("q") cityName: String,
            @Query("APPID") appId: String,
            @Query("units") units: String
    ): Call<WeatherData>
}