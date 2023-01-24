package weather.way.data.common.network

import io.reactivex.rxjava3.core.Single
import weather.way.data.common.dataBase.WeatherDao
import weather.way.domain.ApiRepository
import weather.way.domain.model.*

class RepositoryImpl(
    private val apiService: ApiService,
    private val dao: WeatherDao
) : ApiRepository {

    override fun getHourlyForecastByName(cityName: String): Single<CommonInfo> {
        val weatherList = dao.getAllWeatherList()
        val forecast = apiService.getHourlyForecastByName(cityName)
        return Single.zip(weatherList, forecast) { daoResult, responseResult ->
            val isInFavorite = daoResult.find { it.name == cityName } != null
            CommonInfo(
                cod = responseResult.cod,
                message = responseResult.message,
                cnt = responseResult.cnt,
                list = responseResult.list.map { hourlyForecastDto ->
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
                        wind = Wind(
                            speed = hourlyForecastDto.wind.speed,
                            deg = hourlyForecastDto.wind.deg,
                            gust = hourlyForecastDto.wind.gust
                        ),
                        visibility = hourlyForecastDto.visibility,
                        pop = hourlyForecastDto.pop,
                        dt_txt = hourlyForecastDto.dt_txt,
                        clouds = Clouds(
                            all = hourlyForecastDto.cloud.all
                        )
                    )
                },
                city = City(
                    id = responseResult.city.id,
                    name = responseResult.city.name,
                    coord = Coord(
                        lon = responseResult.city.coord.lon,
                        lat = responseResult.city.coord.lat
                    ),
                    country = responseResult.city.country,
                    population = responseResult.city.population,
                    timezone = responseResult.city.timezone,
                    sunrise = responseResult.city.sunrise,
                    sunset = responseResult.city.sunset
                ),
                isInFavourite = isInFavorite
            )
        }
    }

    override fun getHourlyForecastByGeo(lon: String?, lat: String?): Single<CommonInfo> {
        val weatherList = dao.getAllWeatherList()
        val forecast = apiService.getHourlyForecastByGeo(lon, lat)
        return Single.zip(weatherList, forecast) { daoResult, responseResult ->
            val isInFavourite = daoResult.find { it.lon.toString() == lon && it.lat.toString() == lat } != null
            CommonInfo(
                cod = responseResult.cod,
                message = responseResult.message,
                cnt = responseResult.cnt,
                list = responseResult.list.map { hourlyForecastDto ->
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
                        wind = Wind(
                            speed = hourlyForecastDto.wind.speed,
                            deg = hourlyForecastDto.wind.deg,
                            gust = hourlyForecastDto.wind.gust
                        ),
                        visibility = hourlyForecastDto.visibility,
                        pop = hourlyForecastDto.pop,
                        dt_txt = hourlyForecastDto.dt_txt,
                        clouds = Clouds(
                            all = hourlyForecastDto.cloud.all
                        )
                    )
                },
                city = City(
                    id = responseResult.city.id,
                    name = responseResult.city.name,
                    coord = Coord(
                        lon = responseResult.city.coord.lon,
                        lat = responseResult.city.coord.lat
                    ),
                    country = responseResult.city.country,
                    population = responseResult.city.population,
                    timezone = responseResult.city.timezone,
                    sunrise = responseResult.city.sunrise,
                    sunset = responseResult.city.sunset
                ),
                isInFavourite = isInFavourite
            )
        }
    }
}