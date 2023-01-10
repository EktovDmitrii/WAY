package weather.way.data.common.dataBase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import weather.way.data.common.dataBase.entities.WeatherDbModel


class ListConvertor {
    @TypeConverter
    fun fromList(value: List<WeatherDbModel>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String?): List<WeatherDbModel>? {
        val gson = Gson()
        val type = object : TypeToken<List<WeatherDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}