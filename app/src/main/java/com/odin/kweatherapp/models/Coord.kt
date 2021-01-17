package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon") @Expose
    var lon: Double? = null,

    @SerializedName("lat")
    @Expose
    val lat: Double? = null
)