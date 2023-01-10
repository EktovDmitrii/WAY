package weather.way.data.common.dataBase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import weather.way.data.common.dataBase.entities.CityDbModel
import weather.way.data.common.dataBase.entities.ForecastDbModel
import weather.way.data.common.dataBase.entities.WeatherDbModel

@TypeConverters(ListConvertor::class)
@Database(
    entities = [CityDbModel::class, ForecastDbModel::class, WeatherDbModel::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "weather.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            kotlin.synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}