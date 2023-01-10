package weather.way.domain.model

import weather.way.data.common.dataBase.entities.CityDbModel

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)
fun City.toDbEntity(): CityDbModel {
    return CityDbModel(
        id, name, coord.lon, coord.lat, country, population, timezone, sunrise, sunset
    )
}
