package weather.way.data.common.network.apiResponse

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val all: Int
)