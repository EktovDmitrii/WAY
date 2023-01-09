package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo

class GetForecastByNameUseCase(private val repository: ApiRepository) {
    fun getForecastByName(cityName: String): Single<CommonInfo> {
        return repository.getHourlyForecastByName(cityName)
    }
}