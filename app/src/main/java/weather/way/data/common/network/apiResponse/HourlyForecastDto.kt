package weather.way.data.common.network.apiResponse

import com.google.gson.annotations.SerializedName

data class HourlyForecastDto(
    @SerializedName("dt") val dt : Int,
    @SerializedName("main") val main : MainWeatherDto,
    @SerializedName("weather") val weather : List<WeatherDto>,
    @SerializedName("wind") val wind : WindDto,
    @SerializedName("clouds") val cloud: CloudsDto,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("pop") val pop : Double,
    @SerializedName("dt_txt") val dt_txt : String
)
