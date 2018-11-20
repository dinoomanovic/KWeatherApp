package com.odin.kweatherapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import com.odin.kweatherapp.Models.CityPreferences
import com.odin.kweatherapp.Models.WeatherData
import com.odin.kweatherapp.Utils.WeatherApi
import com.odin.kweatherapp.Utils.WeatherConstants
import com.odin.kweatherapp.Utils.WeatherRestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class WeatherFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val weatherView : View? = inflater?.inflate(R.layout.weather_fragment, container, false)
        val weatherArrayList = ArrayList<WeatherData?>()
        val progressBar : ProgressBar = weatherView?.findViewById(R.id.progressBar) as ProgressBar
        val weatherList : ListView = weatherView.findViewById(R.id.mainList) as ListView
        var weatherAdapter: WeatherAdapter
        val cityPreferences = CityPreferences(activity)
        progressBar.visibility = View.VISIBLE
        val adapter = WeatherRestAdapter()
        val client : WeatherApi = adapter.createService()
        val call : Call<WeatherData> = client.getWeather(cityPreferences.getCity(), WeatherConstants.APPID, WeatherConstants.METRIC)
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherData : WeatherData? = response.body()
                weatherArrayList.add(weatherData)
                weatherAdapter = WeatherAdapter(activity, R.id.mainList, weatherArrayList)
                weatherAdapter.notifyDataSetChanged()
                weatherList.setAdapter(weatherAdapter)
                progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
        return weatherView
    }
}