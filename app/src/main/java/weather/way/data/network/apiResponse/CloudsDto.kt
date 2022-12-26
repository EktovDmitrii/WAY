package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all") val all: Int

)
