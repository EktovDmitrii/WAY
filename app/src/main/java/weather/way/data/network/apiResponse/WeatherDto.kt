package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("id") val id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)
