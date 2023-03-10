package weather.way.ui.weather

import weather.way.domain.model.CommonInfo

interface WeatherPresenter {

    fun getHourlyForecast(lon: String, lat: String)

    fun getForecastByName(cityName: String)

    fun addToFavourite(commonInfo: CommonInfo)

    fun onDestroy()
}