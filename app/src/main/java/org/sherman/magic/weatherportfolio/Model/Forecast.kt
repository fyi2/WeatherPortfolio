package org.sherman.magic.weatherportfolio.Model

import java.io.Serializable

/**
 * Created by Admin on 12/9/2017.
 */
public class Forecast : Serializable {
    var forecastDate: String? = null
    var forecastHighTemp: String? = null
    var forecastLowTemp: String? = null
    var forecastWeatherDescription: String? = null
}