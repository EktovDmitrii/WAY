package weather.way.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import weather.way.data.network.apiResponse.ApiResponseMain

interface ApiService {

    @GET("/data/2.5/weather")
    fun getCurrentWeatherInCity(
        @Query(PATH_PARAM_CITY_NAME) cityName: String,
        @Query(QUERY_PARAM_APP_ID) appId: String = APP_ID_VALUE,
    ): Single<ApiResponseMain>

    companion object {
        private const val PATH_PARAM_CITY_NAME = "q"
        private const val QUERY_PARAM_APP_ID = "appid"
        private const val APP_ID_VALUE = "d5242d25373507cc264933041d3306df"
    }
}