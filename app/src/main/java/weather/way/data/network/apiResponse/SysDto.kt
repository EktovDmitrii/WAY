package weather.way.data.network.apiResponse

import com.google.gson.annotations.SerializedName

data class SysDto(

    @SerializedName("pod") val pod : String
)
