package weather.way.data.common.network.apiResponse

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)
