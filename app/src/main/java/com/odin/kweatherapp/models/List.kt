package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class List(
    @SerializedName("dt") @Expose
     var dt: Int? = null,

    @SerializedName("main")
    @Expose
     val main: Main? = null,

    @SerializedName("weather")
    @Expose
     val weather: List<Weather>? = null,

    @SerializedName("clouds")
    @Expose
     val clouds: Clouds? = null,

    @SerializedName("wind")
    @Expose
     val wind: Wind? = null,

    @SerializedName("rain")
    @Expose
     val rain: Rain? = null,

    @SerializedName("sys")
    @Expose
     val sys: Sys_? = null,

    @SerializedName("dt_txt")
    @Expose
     val dtTxt: String? = null,

    @SerializedName("snow")
    @Expose
     val snow: Snow? = null
)