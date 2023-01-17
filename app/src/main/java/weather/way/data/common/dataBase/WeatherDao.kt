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

    @Query("DELETE FROM common_info WHERE id=:cityName")
    fun deleteCity(cityName: String): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWeatherInfo(commonInfoDbModel: CommonInfoDbModel): Completable

    @Query("SELECT * FROM common_info")
    fun getAllWeatherList(): Single<List<CommonInfoDbModel>>
}