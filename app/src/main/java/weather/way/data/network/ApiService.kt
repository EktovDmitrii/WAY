package weather.way.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import weather.way.data.network.apiResponse.ApiResponseHourlyForecast

interface ApiService {
//
//    @GET("/data/2.5/weather")
//    fun getCurrentWeatherInCity(
//        @Query(PATH_PARAM_CITY_NAME) cityName: String,
//        @Query(QUERY_PARAM_APP_ID) appId: String = APP_ID_VALUE,
//    ): Single<ApiResponseMain>

    @GET("data/2.5/forecast")
    fun getHourlyForecast(
        @Query(LAT) lat: String = LAT_VALUE,
        @Query(LON) lon: String = LON_VALUE,
        @Query(TIME_PERIOD) exclude: String = TIME_PERIOD_NAME,
        @Query(QUERY_PARAM_APP_ID) appId: String = APP_ID_VALUE,
    ): Single<ApiResponseHourlyForecast>

    companion object {
        private const val PATH_PARAM_CITY_NAME = "q"
        private const val LAT = "lat"
        private const val LON = "lon"
        private const val LAT_VALUE = "55.75"
        private const val LON_VALUE = "37.62"
        private const val TIME_PERIOD = "exclude"
        private const val TIME_PERIOD_NAME = "hourly,daily"
        private const val QUERY_PARAM_APP_ID = "appid"
        private const val APP_ID_VALUE = "d5242d25373507cc264933041d3306df"
    }
}