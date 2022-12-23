package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class ApiResponseMain(

    @SerializedName("coord") val coord: CoordDto,
    @SerializedName("weather") val weatherDto: List<WeatherDto>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainWeatherData,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sun,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)
