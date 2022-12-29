package weather.way.domain.model

import java.io.Serializable

data class HourlyForecast(
    val dt: Int,
    val main: MainWeather,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
) : Serializable
