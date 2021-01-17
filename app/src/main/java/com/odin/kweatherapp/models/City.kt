package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("coord")
    @Expose
    val coord: Coord? = null,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("population")
    @Expose
    val population: Int? = null,

    @SerializedName("sys")
    @Expose
    val sys: Sys? = null
)