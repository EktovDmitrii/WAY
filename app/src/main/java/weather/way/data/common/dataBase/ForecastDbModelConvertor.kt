package weather.way.data.common.dataBase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import weather.way.data.common.dataBase.entities.ForecastDbModel

class ForecastDbModelConvertor {
    @TypeConverter
    fun fromList(value: List<ForecastDbModel>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastDbModel>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String?): List<ForecastDbModel>? {
        val gson = Gson()
        val type = object : TypeToken<List<ForecastDbModel>>() {}.type
        return gson.fromJson(value, type)
    }
}