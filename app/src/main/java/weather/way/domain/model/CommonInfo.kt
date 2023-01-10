package weather.way.domain.model

import java.io.Serializable

data class CommonInfo(

    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<HourlyForecast>,
    val city: City
) : Serializable