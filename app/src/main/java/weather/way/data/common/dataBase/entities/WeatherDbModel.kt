package weather.way.data.common.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import weather.way.domain.model.Weather

@Entity(tableName = "favourite_weather")
data class WeatherDbModel(
    @PrimaryKey
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

fun WeatherDbModel.toModel(): Weather {
    return Weather(id, main, description, icon)
}
