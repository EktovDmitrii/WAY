package weather.way.data.common.dataBase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.entities.WeatherDbModel
import weather.way.data.common.dataBase.entities.toModel
import weather.way.domain.DaoRepository
import weather.way.domain.model.City
import weather.way.domain.model.HourlyForecast
import weather.way.domain.model.Weather
import weather.way.domain.model.toDbEntity

class WeatherDaoImpl(
    private val weatherDao: WeatherDao
) : DaoRepository {
    override fun addCity(city: City): Completable {
        return weatherDao.insertCity(city.toDbEntity())
    }

    override fun addWeather(weather: Weather): Completable {
        return weatherDao.insertWeather(weather.toDbEntity())
    }

    override fun addForecast(hourlyForecast: HourlyForecast): Completable {
        return weatherDao.insertForecast(hourlyForecast.toDbEntity())
    }

    override fun getAllCities(): Single<List<City>> {
        val dbModel = weatherDao.getAllCities()
        return dbModel.map {
            it.map {
                it.toModel()
            }
        }
    }

    override fun getAllWeather(): Single<List<Weather>> {
        val dbModel = weatherDao.getAllWeather()
        return dbModel.map {
            it.map {
                it.toModel()
            }
        }
    }

    override fun getAllForecast(): Single<List<HourlyForecast>> {
        val dbModel = weatherDao.getAllForecast()
        return dbModel.map {
            it.map {
                it.toModel()
            }
        }
    }
}