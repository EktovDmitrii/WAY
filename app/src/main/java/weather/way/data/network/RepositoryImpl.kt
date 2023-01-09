package weather.way.data.network

import io.reactivex.rxjava3.core.Single
import weather.way.domain.ApiRepository
import weather.way.domain.model.*

class RepositoryImpl(private val apiService: ApiService) : ApiRepository {

    override fun getHourlyForecastByName(cityName: String): Single<CommonInfo> {
        return apiService.getHourlyForecastByName(cityName).map { it ->
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

    override fun getHourlyForecastByGeo(lon: String, lat: String): Single<CommonInfo> {
        return apiService.getHourlyForecastByGeo(lon, lat).map { it ->
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