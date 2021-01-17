package com.odin.kweatherapp.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.odin.kweatherapp.R
import com.odin.kweatherapp.models.WeatherData
import com.odin.kweatherapp.utils.getIcon
import kotlinx.android.synthetic.main.weather_details_layout.view.*

class WeatherDetailsAdapter(private val items: ArrayList<WeatherData?>) : RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDetailsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.weather_details_layout,
                parent,
                false
            )
        return WeatherDetailsViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(weatherDetailsViewHolder: WeatherDetailsViewHolder, position: Int) {
        weatherDetailsViewHolder.bind(items[position]!!)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class WeatherDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherData: WeatherData) {
            itemView.detailText?.isSelected = true
            itemView.detailText?.ellipsize = TextUtils.TruncateAt.MARQUEE
            itemView.detailText?.setSingleLine(true)
            itemView.detailText?.text = " < " +
                "Humidity: " + weatherData?.list?.get(0)?.main?.humidity +
                " | " + "Wind: " + weatherData?.list?.get(0)?.wind?.speed +
                " | " + "Pressure: " + weatherData?.list?.get(0)?.main?.pressure +
                " | " + "Deg: " + weatherData?.list?.get(0)?.wind?.deg +
                " | " + "Latitude: " + weatherData?.city?.coord?.lat +
                " | " + "Longitude: " + weatherData.city?.coord?.lon +
                " > "

            itemView.textDay1?.text = weatherData.list?.get(8)?.weather?.get(0)?.description
            itemView.textDay2?.text = weatherData.list?.get(16)?.weather?.get(0)?.description
            itemView.textDay3?.text = weatherData.list?.get(24)?.weather?.get(0)?.description

            itemView.dayTemp1?.text = Math.round(weatherData.list?.get(8)?.main?.temp!!).toString() + " °C"
            itemView.dayTemp2?.text = Math.round(weatherData.list[16].main?.temp!!).toString() + " °C"
            itemView.dayTemp3?.text = Math.round(weatherData.list[24].main?.temp!!).toString() + " °C"

            itemView.imageDay1?.setImageDrawable(ContextCompat.getDrawable(
                itemView.context, (weatherData.list[8].weather?.get(0)?.icon!!).getIcon()))
            itemView.imageDay2?.setImageDrawable(ContextCompat.getDrawable(
                itemView.context, (weatherData.list[16].weather?.get(0)?.icon!!).getIcon()))
            itemView.imageDay3?.setImageDrawable(ContextCompat.getDrawable(
                itemView.context, (weatherData.list[24].weather?.get(0)?.icon)!!.getIcon()))
        }
    }
}