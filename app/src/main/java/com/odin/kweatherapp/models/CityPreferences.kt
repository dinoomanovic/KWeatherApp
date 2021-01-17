package com.odin.kweatherapp.models

import android.content.SharedPreferences

class CityPreferences(private val preferences: SharedPreferences) {
    fun getCity(): String? {
        return preferences.getString("city", "Tuzla,BA")
    }

    fun setCity(city: String) {
        preferences.edit().putString("city", city).apply()
    }
}
