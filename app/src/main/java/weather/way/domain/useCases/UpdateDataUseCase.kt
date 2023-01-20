package weather.way.domain.useCases

import io.reactivex.rxjava3.core.Completable
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo

class UpdateDataUseCase(private val repository: DaoRepository) {
    fun updateWeatherData(commonInfo: CommonInfo): Completable {
        return repository.updateData(commonInfo)
    }
}