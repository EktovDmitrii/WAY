package weather.way.domain.model

import weather.way.data.common.dataBase.entities.CommonInfoDbModel
import weather.way.data.common.dataBase.entities.ForecastDbModel
import weather.way.data.common.dataBase.entities.WeatherDbModel
import java.io.Serializable

data class CommonInfo(

    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<HourlyForecast>,
    val city: City,
    var isInFavourite: Boolean
) : Serializable

fun CommonInfo.toDbEntity(): CommonInfoDbModel {
    return CommonInfoDbModel(
        cod, message, cnt, list.map {
            ForecastDbModel(
                dt = it.dt,
                it.main.temp,
                it.main.feels_like,
                it.main.temp_min,
                it.main.temp_max,
                it.main.pressure,
                it.main.sea_level,
                it.main.grnd_level,
                it.main.humidity,
                it.main.temp_kf,
                it.weather.map { weather ->
                    WeatherDbModel(
                        id = weather.id,
                        main = weather.main,
                        description = weather.description,
                        icon = weather.icon
                    )
                },
                it.wind.speed,
                it.wind.deg,
                it.wind.gust,
                it.visibility,
                it.pop,
                it.dt_txt
            )
        },
        city.id,
        city.name,
        city.coord.lon,
        city.coord.lat,
        city.country,
        city.population,
        city.timezone,
        city.sunrise,
        city.sunset
    )
}