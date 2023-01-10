package weather.way.data.common.network.apiResponse

import com.google.gson.annotations.SerializedName

data class ApiResponseHourlyForecast(
    @SerializedName("cod") val cod : Int,
    @SerializedName("message") val message : Int,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<HourlyForecastDto>,
    @SerializedName("city") val city : CityDto
)

