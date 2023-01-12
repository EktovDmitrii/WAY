package weather.way.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.domain.model.City
import weather.way.domain.model.CommonInfo
import weather.way.domain.model.HourlyForecast
import weather.way.domain.model.Weather

interface DaoRepository {

//    fun addCity(city: City): Completable
//
//    fun addWeather(weather: Weather): Completable
//
//    fun addForecast(hourlyForecast: HourlyForecast): Completable
//
//    fun getAllCities(): Single<List<City>>
//
//    fun getAllWeather(): Single<List<Weather>>
//
//    fun getAllForecast(): Single<List<HourlyForecast>>


    fun addCity(commonInfo: CommonInfo): Completable

    fun getCityWeatherList():Single<List<CommonInfo>>
}