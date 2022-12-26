package weather.way.domain

import io.reactivex.rxjava3.core.Single
import weather.way.domain.model.CommonInfo

interface ApiRepository {

    fun getCurrentWeatherInCity(cityName: String): Single<CommonInfo>
}