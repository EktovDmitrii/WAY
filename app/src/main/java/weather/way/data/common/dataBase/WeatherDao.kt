package weather.way.data.common.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.entities.CommonInfoDbModel

@Dao
interface WeatherDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCity(cityDbModel: CityDbModel): Completable
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertWeather(weatherDbModel: WeatherDbModel): Completable
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllWeatherInfo(forecastDbModel: ForecastDbModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWeatherInfo(commonInfoDbModel: CommonInfoDbModel): Completable
//
//    @Query("SELECT * FROM favourite_city")
//    fun getAllCities(): Single<List<CityDbModel>>
//
//    @Query("SELECT * FROM favourite_weather")
//    fun getAllWeather(): Single<List<WeatherDbModel>>
//
//    @Query("SELECT * FROM favourite_forecast")
//    fun getAllForecast(): Single<List<ForecastDbModel>>

    @Query("SELECT * FROM common_info")
    fun getAllWeatherList(): Single<List<CommonInfoDbModel>>
}