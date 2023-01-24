package weather.way.data.common.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import weather.way.data.common.dataBase.entities.CommonInfoDbModel
import weather.way.data.common.dataBase.entities.ForecastDbModel
import weather.way.data.common.dataBase.entities.WeatherDbModel

@TypeConverters(WeatherDbModelConvertor::class, ForecastDbModelConvertor::class)
@Database(
    entities = [ForecastDbModel::class, WeatherDbModel::class, CommonInfoDbModel::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DB_NAME = "weather.db"
    }
}