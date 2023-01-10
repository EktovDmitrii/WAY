package weather.way.domain.model

import weather.way.data.common.dataBase.entities.ForecastDbModel
import weather.way.data.common.dataBase.entities.WeatherDbModel
import java.io.Serializable

data class HourlyForecast(
    val dt: Int,
    val main: MainWeather,
    val weather: List<Weather>,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val dt_txt: String
) : Serializable

fun HourlyForecast.toDbEntity(): ForecastDbModel {
    return ForecastDbModel(
        dt,
        main.temp,
        main.feels_like,
        main.temp_min,
        main.temp_max,
        main.pressure,
        main.sea_level,
        main.grnd_level,
        main.humidity,
        main.temp_kf,
        weather.map {
            WeatherDbModel(
                id = it.id,
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        wind.speed,
        wind.deg,
        wind.gust,
        visibility,
        pop,
        dt_txt
    )
}