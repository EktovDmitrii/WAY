package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class HourlyForecastDto(
    @SerializedName("dt") val dt : Int,
    @SerializedName("main") val main : MainWeatherDto,
    @SerializedName("weather") val weather : List<WeatherDto>,
    @SerializedName("clouds") val clouds : CloudsDto,
    @SerializedName("wind") val wind : WindDto,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("pop") val pop : Double,
    @SerializedName("sys") val sys : SysDto,
    @SerializedName("dt_txt") val dt_txt : String
)
