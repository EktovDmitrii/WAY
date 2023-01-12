package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Single
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo

class GetFavouriteListUseCase(private val repository: DaoRepository) {
    fun getFavouriteList(): Single<List<CommonInfo>> {
        return repository.getCityWeatherList()
    }
}