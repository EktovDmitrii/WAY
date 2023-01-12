package weather.way.data.common.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import weather.way.data.common.dataBase.WeatherDbModelConvertor
import weather.way.domain.model.*

@Entity(tableName = "favourite_forecast")
data class ForecastDbModel(
    @PrimaryKey
    val dt: Int,
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double,
    @TypeConverters(WeatherDbModelConvertor::class)
    val weather: List<WeatherDbModel>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val visibility: Int,
    val pop: Double,
    val dt_txt: String
)

fun ForecastDbModel.toModel(): HourlyForecast {
    return HourlyForecast(
        dt,
        MainWeather(
            temp,
            feels_like,
            temp_min,
            temp_max,
            pressure,
            sea_level,
            grnd_level,
            humidity,
            temp_kf
        ),
        weather.map { weatherDbModel ->
            Weather(id = weatherDbModel.id,
            main = weatherDbModel.main,
            description = weatherDbModel.description,
            icon = weatherDbModel.icon)
        },
        Wind(speed, deg, gust),
        visibility,
        pop,
        dt_txt
    )
}
