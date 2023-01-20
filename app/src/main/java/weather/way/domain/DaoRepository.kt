package weather.way.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.domain.model.City
import weather.way.domain.model.CommonInfo
import weather.way.domain.model.HourlyForecast
import weather.way.domain.model.Weather

interface DaoRepository {

    fun deleteCity(commonInfo: CommonInfo): Completable

    fun addCity(commonInfo: CommonInfo): Completable

    fun getCityWeatherList():Single<List<CommonInfo>>

    fun updateData(commonInfo: CommonInfo): Completable
}