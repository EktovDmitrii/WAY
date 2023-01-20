package weather.way.data.common.dataBase

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.entities.CommonInfoDbModel
import kotlin.reflect.KClass

@Dao
interface WeatherDao {

    @Query("DELETE FROM common_info WHERE id=:id")
    fun deleteCity(id: Int): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWeatherInfo(commonInfoDbModel: CommonInfoDbModel): Completable

    @Query("SELECT * FROM common_info")
    fun getAllWeatherList(): Single<List<CommonInfoDbModel>>

    @Update
    fun updateData(commonInfoDbModel: CommonInfoDbModel): Completable
}