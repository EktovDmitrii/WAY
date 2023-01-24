package weather.way.data.common.dataBase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.entities.toModel
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.model.toDbEntity

class DaoRepositoryImpl(
    private val weatherDao: WeatherDao
) : DaoRepository {
    override fun addCity(commonInfo: CommonInfo): Completable {
        return weatherDao.insertAllWeatherInfo(commonInfo.toDbEntity())
    }

    override fun getCityWeatherList(): Single<List<CommonInfo>> {
        return weatherDao.getAllWeatherList().map { it ->
            it.map {
                it.toModel()
            }
        }
    }

    override fun deleteCity(commonInfo: CommonInfo): Completable {
        return weatherDao.deleteCity(commonInfo.city.id)
    }

    override fun updateData(commonInfo: CommonInfo): Completable {
        return weatherDao.updateData(commonInfo.toDbEntity())
    }
}