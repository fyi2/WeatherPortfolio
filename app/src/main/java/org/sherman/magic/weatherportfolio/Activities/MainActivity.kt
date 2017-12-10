package org.sherman.magic.weatherportfolio.Activities

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.sherman.magic.weatherportfolio.Adapter.WeatherAdapter
import org.sherman.magic.weatherportfolio.Data.DEBUG
import org.sherman.magic.weatherportfolio.Data.Weather
import org.sherman.magic.weatherportfolio.Data.WeatherRetriever
import org.sherman.magic.weatherportfolio.Model.Forecast
import org.sherman.magic.weatherportfolio.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var forecastList:ArrayList<Forecast>
    var forecastAdapter:WeatherAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("org.sherman.magic.weatherportfolio", android.content.Context.MODE_PRIVATE)
        val chosenCity = sharedPreferences!!.getString("chosenLocation", "Marlborough MA")
        locationNameId.setText(chosenCity.toString())
        runSearch(locationNameId)
    }

    fun runSearch(view:View){
        forecastList = ArrayList<Forecast>()
        var retriver = WeatherRetriever()

        val callback = object:Callback<Weather>{
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                val city = response?.body()?.query?.results?.channel?.location?.city
                val region = response?.body()?.query?.results?.channel?.location?.region
                val temperature = response?.body()?.query?.results?.channel?.item?.condition?.temp
                val html = response?.body()?.query?.results?.channel?.item?.description
                val conditions = response?.body()?.query?.results?.channel?.item?.condition?.text
                val date = response?.body()?.query?.results?.channel?.item?.condition?.date
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                if (forecasts != null){
                    // Set Up the main panel
                    currentDateId.text = trimDate(date!!)
                    currentTempId.text = temperature+"F"
                    locationTextViewId.text = city+",\n"+region
                    val url = getURL(html!!)
                    Picasso.with(applicationContext)
                            .load(url)
                            .into(weatherIcon)


                    // Set Up the recycler View
                    for (forecast in forecasts){
                        var fc = Forecast()
                        fc.forecastLowTemp = forecast.low
                        fc.forecastHighTemp = forecast.high
                        fc.forecastDate = forecast.date
                        fc.forecastWeatherDescription = forecast.text

                        forecastList.add(fc)

                        forecastAdapter = WeatherAdapter(forecastList, applicationContext)
                        layoutManager = LinearLayoutManager(applicationContext)

                        viewPager.layoutManager = layoutManager
                        viewPager.adapter = forecastAdapter
                    }
                    forecastAdapter!!.notifyDataSetChanged()
                }

            }
            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                Log.d(DEBUG, "Weather API Failed")
            }

        }
        // Update Shared Preferences
        sharedPreferences?.edit()?.putString("chosenLocation",locationNameId.text.toString() )?.apply()

        retriver.getForecast(callback, locationNameId.text.toString())

    }

    fun getURL(url:String):String{
        val bits = url.split('\"')
        Log.d(DEBUG,bits[1])
        return bits[1]
    }

    fun trimDate(date:String):String {
        val elements = date.split(" ")
        return elements[0]+" "+elements[1]+" "+elements[2]+" "+elements[3]
    }
}
