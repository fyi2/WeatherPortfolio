package org.sherman.magic.weatherportfolio.Data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Admin on 12/9/2017.
 */
interface  WeatherAPI {
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    fun getForecast(@Query("q") q:String): Call<Weather>
}

class Weather(val query: WeatherQuery)
class WeatherQuery(val results: WeatherResults)
class WeatherResults(val channel: WeatherChannel)
class WeatherChannel(val location: WeatherLocation, val item: WeatherItem)
class WeatherLocation(val city: String, val region:String)
class WeatherItem(val forecast: List<WeatherForecast>, val condition: WeatherCondition, val description:String)
class WeatherForecast(val date:String, val day:String, val high:String, val low:String, val text:String)
class WeatherCondition(val date:String, val text: String, val temp:String)

class WeatherRetriever {
    val service : WeatherAPI
    init {
        val retrofit = Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }
    fun getForecast(callback: Callback<Weather>, searchTerm:String) {
        //q = the YAHOO SQL with searchTerm inserted
        val q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"$searchTerm\")"
        Log.d(DEBUG, q)
        val call = service.getForecast(q)
        call.enqueue(callback)
    }
}