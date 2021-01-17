package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") @Expose
    var temp: Double? = null,

    @SerializedName("temp_min")
    @Expose val tempMin: Double? = null,

    @SerializedName("temp_max")
    @Expose
    private val tempMax: Double? = null,

    @SerializedName("pressure")
    @Expose val pressure: Double? = null,

    @SerializedName("sea_level")
    @Expose
    private val seaLevel: Double? = null,

    @SerializedName("grnd_level")
    @Expose
    private val grndLevel: Double? = null,

    @SerializedName("humidity")
    @Expose val humidity: Double? = null,

    @SerializedName("temp_kf")
    @Expose
    private val tempKf: Double? = null
)