package weather.way.domain.model

import com.google.gson.annotations.SerializedName
import weather.way.data.network.apiResponse.*
import java.io.Serializable

data class CommonInfo(

    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: MainWeather,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sun,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
): Serializable