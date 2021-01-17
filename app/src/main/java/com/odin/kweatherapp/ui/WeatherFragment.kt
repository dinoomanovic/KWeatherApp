package com.odin.kweatherapp.ui

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.odin.kweatherapp.R
import com.odin.kweatherapp.adapters.WeatherAdapter
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val weatherView: View? = inflater.inflate(R.layout.weather_fragment, container, false)
        val preferences: SharedPreferences = requireActivity().getPreferences(Activity.MODE_PRIVATE)

        progressBar?.visibility = View.VISIBLE
        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weatherDataUpdated ->
            when (weatherDataUpdated) {
                is WeatherViewModel.WeatherDataUpdated.WeatherDataUpdatedSuccess -> {
                    rvMainList.adapter = WeatherAdapter(weatherDataUpdated.weatherData).also { it.notifyDataSetChanged() }
                    rvMainList.setHasFixedSize(true)
                    progressBar?.visibility = View.GONE
                }
                is WeatherViewModel.WeatherDataUpdated.WeatherDataUpdatedError -> {
                    progressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), weatherDataUpdated.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.fetchWeatherData(preferences)

        return weatherView
    }
}