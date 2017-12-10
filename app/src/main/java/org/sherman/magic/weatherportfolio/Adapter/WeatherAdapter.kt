package org.sherman.magic.weatherportfolio.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_forecast.view.*
import org.sherman.magic.weatherportfolio.Model.Forecast
import org.sherman.magic.weatherportfolio.R


class WeatherAdapter(val forecasts:ArrayList<Forecast>, val context: Context): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_forecast, parent, false)
        return(ViewHolder(view))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder!=null){
            holder.bindView(forecasts[position])
        }

    }

    override fun getItemCount(): Int {
        return forecasts.size

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var forecastHigh = itemView.findViewById<TextView>(R.id.forecastHighText)
        var forecastLow = itemView.findViewById<TextView>(R.id.forecastLowText)
        var forecastDate = itemView.findViewById<TextView>(R.id.forecastDateText)
        var forecastText = itemView.findViewById<TextView>(R.id.forecastDescriptionTextview)


        fun bindView(forecast: Forecast){
            forecastHigh.text = forecast.forecastHighTemp
            forecastLow.text = forecast.forecastLowTemp
            forecastDate.text = forecast.forecastDate
            forecastText.text = forecast.forecastWeatherDescription
        }

    }
}
