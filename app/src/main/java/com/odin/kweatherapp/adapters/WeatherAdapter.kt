package com.odin.kweatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.odin.kweatherapp.R
import com.odin.kweatherapp.models.WeatherData
import com.odin.kweatherapp.utils.getIcon
import kotlinx.android.synthetic.main.weather_layout.view.*
import java.util.ArrayList

class WeatherAdapter(private val items: ArrayList<WeatherData?>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.weather_layout,
                parent,
                false
            )
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(items[position]!!)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherData: WeatherData) {
            itemView.placeId?.text = weatherData.city?.name
            itemView.tempId?.text = Math.round(weatherData?.list!![0].main?.temp!!).toString() + " °C"
            itemView.dayTempId?.text = weatherData.list[0].weather?.get(0)?.description
            itemView.weatherImg?.setImageDrawable(ContextCompat.getDrawable(
                itemView.context, (weatherData.list[0].weather?.get(0)?.icon!!).getIcon()))
        }
    }
}