package weather.way.data.common.dataBase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.entities.toModel
import weather.way.domain.DaoRepository
import weather.way.domain.model.*

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

    //    override fun addCity(city: City): Completable {
//        return weatherDao.insertCity(city.toDbEntity())
//    }
//
//    override fun addWeather(weather: Weather): Completable {
//        return weatherDao.insertWeather(weather.toDbEntity())
//    }
//
//    override fun addForecast(hourlyForecast: HourlyForecast): Completable {
//        return weatherDao.insertForecast(hourlyForecast.toDbEntity())
//    }
//
//    override fun getAllCities(): Single<List<City>> {
//        val dbModel = weatherDao.getAllCities()
//        return dbModel.map {
//            it.map {
//                it.toModel()
//            }
//        }
//    }
//
//    override fun getAllWeather(): Single<List<Weather>> {
//        val dbModel = weatherDao.getAllWeather()
//        return dbModel.map {
//            it.map {
//                it.toModel()
//            }
//        }
//    }
//
//    override fun getAllForecast(): Single<List<HourlyForecast>> {
//        val dbModel = weatherDao.getAllForecast()
//        return dbModel.map {
//            it.map {
//                it.toModel()
//            }
//        }
//    }
}