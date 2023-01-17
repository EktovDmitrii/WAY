package weather.way.data.common.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import weather.way.data.common.dataBase.ForecastDbModelConvertor
import weather.way.domain.model.*

@Entity(tableName = "common_info")
data class CommonInfoDbModel(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    @TypeConverters(ForecastDbModelConvertor::class)
    val list: List<ForecastDbModel>,
    @PrimaryKey
    val id: Int,
    val name: String,
    val lon: Double,
    val lat: Double,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

fun CommonInfoDbModel.toModel(): CommonInfo {
    return CommonInfo(
        cod, message, cnt, list.map {
            HourlyForecast(
                dt = it.dt,
                MainWeather(
                    it.temp,
                    it.feels_like,
                    it.temp_min,
                    it.temp_max,
                    it.pressure,
                    it.sea_level,
                    it.grnd_level,
                    it.humidity,
                    it.temp_kf
                ),
                it.weather.map { weatherDbModel ->
                    Weather(
                        id = weatherDbModel.id,
                        main = weatherDbModel.main,
                        description = weatherDbModel.description,
                        icon = weatherDbModel.icon
                    )
                },
                Wind(it.speed, it.deg, it.gust),
                it.visibility,
                it.pop,
                it.dt_txt
            )
        },
        City(
            id,
            name,
            Coord(lon, lat),
            country,
            population,
            timezone,
            sunrise,
            sunset
        ),
    isInFavourite = false
    )
}