package com.odin.kweatherapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys_(
    @SerializedName("pod")
    @Expose
    val pod: String
)