package weather.way.domain

import io.reactivex.rxjava3.core.Single
import weather.way.domain.model.CommonInfo

interface ApiRepository {

    fun getHourlyForecastByName(cityName: String): Single<CommonInfo>

    fun getHourlyForecastByGeo(lon: String, lat: String): Single<CommonInfo>
}