package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo

class GetHourlyForecastUseCase( private val repository: ApiRepository) {
    fun getHourlyForecast(lon: String, lat: String): Single<CommonInfo> {
       return repository.getHourlyForecastByGeo(lon, lat)
    }
}