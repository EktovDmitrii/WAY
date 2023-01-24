package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Completable
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo

class AddCityToFavouriteUseCase(private val repository: DaoRepository) {
    fun addCityToFavourite(commonInfo: CommonInfo): Completable {
        return repository.addCity(commonInfo)
    }
}