package com.odin.kweatherapp

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.odin.kweatherapp.Models.WeatherData

import java.util.ArrayList

class DetailAdapter(context: Context?, resource: Int, objects: ArrayList<out WeatherData>?) : ArrayAdapter<WeatherData>(context, resource, objects) {
    private var listData : ArrayList<out WeatherData>? = objects

    override fun getItem(position: Int): WeatherData {
        return listData!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder : ViewHolder = ViewHolder()
        var view : View? = convertView
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.detail_layout, parent, false)
            holder.detailText = view.findViewById(R.id.detailText) as TextView?
            holder.imageDay1 = view.findViewById(R.id.imageDay1) as ImageView?
            holder.imageDay2 = view.findViewById(R.id.imageDay2) as ImageView?
            holder.imageDay3 = view.findViewById(R.id.imageDay3) as ImageView?
            holder.day1 = view.findViewById(R.id.textDay1) as TextView?
            holder.day2 = view.findViewById(R.id.textDay2) as TextView?
            holder.day3 = view.findViewById(R.id.textDay3) as TextView?
            holder.dayTemp1 = view.findViewById(R.id.dayTemp1) as TextView?
            holder.dayTemp2 = view.findViewById(R.id.dayTemp2) as TextView?
            holder.dayTemp3 = view.findViewById(R.id.dayTemp3) as TextView?
            view.setTag(holder)
        }
        else
        holder =  view?.getTag() as ViewHolder
        var weather : WeatherData = listData!!.get(position)
        holder.detailText?.setSelected(true)
        holder.detailText?.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        holder.detailText?.setSingleLine(true)
        holder.detailText?.setText(
                " < " +
                "Humidity: " + weather.list[0].main.humidity +
                " | " + "Wind: " + weather.list[0].wind.speed +
                " | " + "Pressure: " + weather.list[0].main.pressure +
                " | " + "Deg: " + weather.list[0].wind.deg +
                " | " + "Latitude: " + weather.city.coord.lat +
                " | " + "Longitude: " + weather.city.coord.lon +
                " > ")
        holder.day1?.setText(weather.list[8].weather[0].description)
        holder.day2?.setText(weather.list[16].weather[0].description)
        holder.day3?.setText(weather.list[24].weather[0].description)
        holder.dayTemp1?.setText(Math.round(weather.list[8].main.temp).toString() + " °C")
        holder.dayTemp2?.setText(Math.round(weather.list[16].main.temp).toString() + " °C")
        holder.dayTemp3?.setText(Math.round(weather.list[24].main.temp).toString() + " °C")
        val img1 = ContextCompat.getDrawable(view?.getContext(),
                getIcon(weather.list[8].weather[0].icon))
        holder.imageDay1?.setImageDrawable(img1)
        val img2 = ContextCompat.getDrawable(view?.getContext(),
                getIcon(weather.list[16].weather[0].icon))
        holder.imageDay2?.setImageDrawable(img2)

        val img3 = ContextCompat.getDrawable(view?.getContext(),
                getIcon(weather.list[24].weather[0].icon))
        holder.imageDay3?.setImageDrawable(img3)
        return view!!
    }

    override fun getCount(): Int {
        return listData!!.size
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
        var detailText: TextView? = null
        var day1: TextView? = null
        var day2: TextView? = null
        var day3: TextView? = null
        var dayTemp1: TextView? = null
        var dayTemp2: TextView? = null
        var dayTemp3: TextView? = null
        var imageDay1: ImageView? = null
        var imageDay2: ImageView? = null
        var imageDay3: ImageView? = null
    }
}