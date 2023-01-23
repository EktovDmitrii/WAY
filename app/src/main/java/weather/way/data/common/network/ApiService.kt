package weather.way.data.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import weather.way.data.common.network.apiResponse.ApiResponseHourlyForecast
import weather.way.utils.Constants.APP_ID_VALUE
import weather.way.utils.Constants.PATH_PARAM_LAT
import weather.way.utils.Constants.PATH_PARAM_LON
import weather.way.utils.Constants.PATH_PARAM_NAME
import weather.way.utils.Constants.QUERY_PARAM_APP_ID
import weather.way.utils.Constants.TIME_PERIOD
import weather.way.utils.Constants.TIME_PERIOD_NAME

interface ApiService {

    @GET("data/2.5/forecast")
    fun getHourlyForecastByGeo(
        @Query(PATH_PARAM_LON) lon: String?,
        @Query(PATH_PARAM_LAT) lat: String?,
        @Query(TIME_PERIOD) exclude: String = TIME_PERIOD_NAME,
        @Query(QUERY_PARAM_APP_ID) appId: String = APP_ID_VALUE,
    ): Single<ApiResponseHourlyForecast>


    @GET("data/2.5/forecast")
    fun getHourlyForecastByName(
        @Query(PATH_PARAM_NAME) cityName: String,
        @Query(TIME_PERIOD) exclude: String = TIME_PERIOD_NAME,
        @Query(QUERY_PARAM_APP_ID) appId: String = APP_ID_VALUE,
    ): Single<ApiResponseHourlyForecast>
}