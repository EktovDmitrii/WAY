package weather.way.data.network

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.*

class RepositoryImpl(private val apiService: ApiService) : ApiRepository {
//    override fun getCurrentWeatherInCity(cityName: String): Single<CommonInfo> {
//        return apiService.getHourlyForecast(cityName).map { it ->
//            CommonInfo(
//                coord = Coord(
//                    lon = it.coord.lon,
//                    lat = it.coord.lat
//                ),
//                weather = it.weather.map { weatherDto ->
//                    Weather(
//                        id = weatherDto.id,
//                        main = weatherDto.main,
//                        description = weatherDto.description,
//                        icon = weatherDto.icon
//                    )
//                },
//                base = it.base,
//                main = MainWeather(
//                    temp = it.main.temp,
//                    feels_like = it.main.feels_like,
//                    temp_min = it.main.temp_min,
//                    temp_max = it.main.temp_max,
//                    pressure = it.main.pressure,
//                    humidity = it.main.humidity
//                ),
//                visibility = it.visibility,
//                wind = Wind(
//                    speed = it.wind.speed,
//                    deg = it.wind.deg
//                ),
//                clouds = Clouds(all = it.clouds.all),
//                dt = it.dt,
//                sys = Sun(
//                    type = it.sys.type,
//                    id = it.sys.id,
//                    country = it.sys.country,
//                    sunrise = it.sys.sunrise,
//                    sunset = it.sys.sunset
//                ),
//                timezone = it.timezone,
//                id = it.id,
//                name = it.name,
//                cod = it.cod
//            )
//        }
//    }

    override fun getHourlyForecast(lon: String, lat: String): Single<CommonInfo> {
        return apiService.getHourlyForecast(lon, lat).map { it ->
            CommonInfo(
                cod = it.cod,
                message = it.message,
                cnt = it.cnt,
                list = it.list.map { hourlyForecastDto ->
                    HourlyForecast(
                        dt = hourlyForecastDto.dt,
                        main = MainWeather(
                            temp = hourlyForecastDto.main.temp,
                            feels_like = hourlyForecastDto.main.feels_like,
                            temp_min = hourlyForecastDto.main.temp_min,
                            temp_max = hourlyForecastDto.main.temp_max,
                            pressure = hourlyForecastDto.main.pressure,
                            sea_level = hourlyForecastDto.main.sea_level,
                            grnd_level = hourlyForecastDto.main.grnd_level,
                            humidity = hourlyForecastDto.main.humidity,
                            temp_kf = hourlyForecastDto.main.temp_kf
                        ),
                        weather = hourlyForecastDto.weather.map { weatherDto ->
                            Weather(
                                id = weatherDto.id,
                                main = weatherDto.main,
                                description = weatherDto.description,
                                icon = weatherDto.icon
                            )
                        },
                        clouds = Clouds(
                            all = hourlyForecastDto.clouds.all
                        ),
                        wind = Wind(
                            speed = hourlyForecastDto.wind.speed,
                            deg = hourlyForecastDto.wind.deg,
                            gust = hourlyForecastDto.wind.gust
                        ),
                        visibility = hourlyForecastDto.visibility,
                        pop = hourlyForecastDto.pop,
                        sys = Sys(
                            pod = hourlyForecastDto.sys.pod
                        ),
                        dt_txt = hourlyForecastDto.dt_txt
                    )
                },
                city = City(
                    id = it.city.id,
                    name = it.city.name,
                    coord = Coord(
                        lon = it.city.coord.lon,
                        lat = it.city.coord.lat
                    ),
                    country = it.city.country,
                    population = it.city.population,
                    timezone = it.city.timezone,
                    sunrise = it.city.sunrise,
                    sunset = it.city.sunset
                )
            )
        }
    }
}