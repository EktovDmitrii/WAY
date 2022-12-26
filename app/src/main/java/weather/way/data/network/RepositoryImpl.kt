package weather.way.data.network

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.*

class RepositoryImpl(private val apiService: ApiService) : ApiRepository {
    override fun getCurrentWeatherInCity(cityName: String): Single<CommonInfo> {
        return apiService.getCurrentWeatherInCity(cityName).map { it ->
            CommonInfo(
                coord = Coord(
                    lon = it.coord.lon,
                    lat = it.coord.lat
                ),
                weather = it.weather.map { weatherDto ->
                    Weather(
                        id = weatherDto.id,
                        main = weatherDto.main,
                        description = weatherDto.description,
                        icon = weatherDto.icon
                    )
                },
                base = it.base,
                main = MainWeather(
                    temp = it.main.temp,
                    feels_like = it.main.feels_like,
                    temp_min = it.main.temp_min,
                    temp_max = it.main.temp_max,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity
                ),
                visibility = it.visibility,
                wind = Wind(
                    speed = it.wind.speed,
                    deg = it.wind.deg
                ),
                clouds = Clouds(all = it.clouds.all),
                dt = it.dt,
                sys = Sun(
                    type = it.sys.type,
                    id = it.sys.id,
                    country = it.sys.country,
                    sunrise = it.sys.sunrise,
                    sunset = it.sys.sunset
                ),
                timezone = it.timezone,
                id = it.id,
                name = it.name,
                cod = it.cod
            )
        }
    }
}