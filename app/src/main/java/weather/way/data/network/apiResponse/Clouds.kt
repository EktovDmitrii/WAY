package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: Int

)
