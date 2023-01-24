package weather.way.domain.model

import weather.way.data.common.dataBase.entities.WeatherDbModel

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

fun Weather.toDbEntity(): WeatherDbModel {
    return WeatherDbModel(id, main, description, icon)
}
