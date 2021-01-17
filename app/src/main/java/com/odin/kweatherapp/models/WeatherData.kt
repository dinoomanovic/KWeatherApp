package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherData (
    @SerializedName("city")
    @Expose val city: City? = null,

    @SerializedName("cod")
    @Expose
    private val cod: String? = null,

    @SerializedName("message")
    @Expose
    private val message: Double? = null,

    @SerializedName("cnt")
    @Expose
    private val cnt: Int? = null,

    @SerializedName("list")
    @Expose
    val list: kotlin.collections.List<List>? = null
)