package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Completable
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo

class DeleteCityFromFavouriteUseCase(private val repository: DaoRepository) {
    fun deleteCity(commonInfo: CommonInfo): Completable {
        return repository.deleteCity(commonInfo)
    }
}
