package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo

class GetWeatherInCityUseCase(private val repository: ApiRepository) {
    fun getWeatherInCity(cityName: String): Single<CommonInfo> {
        return repository.getCurrentWeatherInCity(cityName)
    }
}