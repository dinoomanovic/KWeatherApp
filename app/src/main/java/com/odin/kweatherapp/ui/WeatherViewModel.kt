package com.odin.kweatherapp.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odin.kweatherapp.models.CityPreferences
import com.odin.kweatherapp.models.WeatherData
import com.odin.kweatherapp.utils.WeatherApi
import com.odin.kweatherapp.utils.WeatherConstants
import com.odin.kweatherapp.utils.WeatherRestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private val _weatherLiveData = MutableLiveData<WeatherDataUpdated>()
    val weatherLiveData: LiveData<WeatherDataUpdated> = _weatherLiveData

    fun fetchWeatherData(preferences: SharedPreferences) {
        val weatherArrayList: java.util.ArrayList<WeatherData?> = arrayListOf()
        val cityPreferences = CityPreferences(preferences)

        val adapter = WeatherRestAdapter()
        val client: WeatherApi = adapter.createService()
        val call: Call<WeatherData> = client.getWeather(cityPreferences.getCity()!!, WeatherConstants.APPID, WeatherConstants.METRIC)
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherData: WeatherData? = response.body()
                weatherArrayList.add(weatherData)

                _weatherLiveData.value = WeatherDataUpdated.WeatherDataUpdatedSuccess(weatherArrayList)
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                _weatherLiveData.value = WeatherDataUpdated.WeatherDataUpdatedError(t.message)
            }
        })
    }

    sealed class WeatherDataUpdated {
        data class WeatherDataUpdatedSuccess(val weatherData: ArrayList<WeatherData?>) : WeatherDataUpdated()
        data class WeatherDataUpdatedError(val errorMessage: String?) : WeatherDataUpdated()
    }
}