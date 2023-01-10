package weather.way.data.common.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import weather.way.domain.model.City
import weather.way.domain.model.Coord

@Entity(tableName = "favourite_city")
data class CityDbModel(
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
fun CityDbModel.toModel(): City {
    return City(id, name, Coord(lon, lat), country, population, timezone, sunrise, sunset)
}
