package org.sherman.magic.weatherportfolio.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.sherman.magic.weatherportfolio.Model.Forecast

import org.sherman.magic.weatherportfolio.R






/**
 * A simple [Fragment] subclass.
 */
class ForecastFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var forecastView: View = inflater!!.inflate(R.layout.fragment_forecast, container, false)

        val forecastDate :TextView = forecastView.findViewById(R.id.forecastDateText)
        val forecastHigh :TextView = forecastView.findViewById(R.id.forecastHighText)
        val forecastLow :TextView = forecastView.findViewById(R.id.forecastLowText)
        val forecastDescription :TextView = forecastView.findViewById(R.id.forecastDescriptionTextview)

        var forecast: Forecast = arguments.getSerializable("forecast") as Forecast

        forecastDate.setText(forecast.forecastDate);
        forecastHigh.setText(forecast.forecastHighTemp);
        forecastLow.setText(forecast.forecastLowTemp);
        forecastDescription.setText(forecast.forecastWeatherDescription);

        return forecastView
    }

    fun newInstance(forecast: Forecast): ForecastFragment {
        val forecastFragment = ForecastFragment()
        val bundle = Bundle()
        bundle.putSerializable("forecast", forecast)

        forecastFragment.arguments = bundle

        return forecastFragment


    }

}// Required empty public constructor
