package com.odin.kweatherapp

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.odin.kweatherapp.Models.WeatherData
import java.util.ArrayList

class WeatherAdapter(context: Context?, resource: Int, objects: ArrayList<WeatherData?>) : ArrayAdapter<WeatherData>(context, resource, objects) {
    private var listData: ArrayList<WeatherData?> = objects

    override fun getItem(position: Int): WeatherData? {
        return listData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder = ViewHolder()
        var view : View? = convertView
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.weather_layout, parent, false)
            holder.cityName = view.findViewById(R.id.placeId) as TextView
            holder.temp = view.findViewById(R.id.tempId) as TextView
            holder.dayTemp = view.findViewById(R.id.dayTempId) as TextView
            holder.icon = view.findViewById(R.id.weatherImg) as ImageView
            view.setTag(holder)
        }
        else
            holder =  view?.getTag() as ViewHolder
            val weather : WeatherData? = listData.get(position)
            holder.cityName?.setText(weather?.city?.name)
            holder.temp?.setText(Math.round(weather?.list!![0].main.temp).toString() + " °C")
            holder.dayTemp?.setText(weather?.list!![0].weather[0].description)
            val img = ContextCompat.getDrawable(view?.getContext(),
                getIcon(weather?.list?.get(0)?.weather?.get(0)?.icon!!))
            holder.icon?.setImageDrawable(img)
        return view!!
    }
    private fun getIcon(iconName: String): Int {
        when (iconName) {
            "01d" -> return R.drawable.d01
            "01n" -> return R.drawable.n01
            "02d" -> return R.drawable.d02
            "02n" -> return R.drawable.n02
            "03d" -> return R.drawable.d03
            "03n" -> return R.drawable.n03
            "04d" -> return R.drawable.d04
            "04n" -> return R.drawable.n04
            "09d" -> return R.drawable.d09
            "09n" -> return R.drawable.n09
            "10d" -> return R.drawable.d10
            "10n" -> return R.drawable.n10
            "11d" -> return R.drawable.d11
            "11n" -> return R.drawable.n11
            "13d" -> return R.drawable.d13
            "13n" -> return R.drawable.n13
            else -> return R.drawable.n01
        }
    }

    class ViewHolder {
        var cityName: TextView? = null
        var temp: TextView? = null
        var dayTemp: TextView? = null
        var icon: ImageView? = null
    }

}
