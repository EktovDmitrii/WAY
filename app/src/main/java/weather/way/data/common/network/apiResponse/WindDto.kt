package weather.way.data.common.network.apiResponse

import com.google.gson.annotations.SerializedName

data class WindDto(

    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust : Double
)
