package com.odin.kweatherapp.Models

import android.app.Activity
import android.content.SharedPreferences

class CityPreferences(activity: Activity) {
    var preferences : SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)
    fun getCity(): String {
        return preferences.getString("city", "Tuzla,BA")
    }
    fun setCity(city: String) {
        preferences.edit().putString("city", city).apply()
    }
}
