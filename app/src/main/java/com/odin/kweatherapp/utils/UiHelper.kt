package com.odin.kweatherapp.utils

import com.odin.kweatherapp.R

fun String.getIcon(): Int =
    when (this) {
        "01d" -> R.drawable.d01
        "01n" -> R.drawable.n01
        "02d" -> R.drawable.d02
        "02n" -> R.drawable.n02
        "03d" -> R.drawable.d03
        "03n" -> R.drawable.n03
        "04d" -> R.drawable.d04
        "04n" -> R.drawable.n04
        "09d" -> R.drawable.d09
        "09n" -> R.drawable.n09
        "10d" -> R.drawable.d10
        "10n" -> R.drawable.n10
        "11d" -> R.drawable.d11
        "11n" -> R.drawable.n11
        "13d" -> R.drawable.d13
        "13n" -> R.drawable.n13
        else -> R.drawable.n01
    }