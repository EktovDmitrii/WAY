package weather.way.ui.favourite

import weather.way.domain.model.CommonInfo

interface FavouritePresenter {

    fun getWeatherList()

    fun getForecastByName(cityName: String)

    fun updateData(commonInfo: CommonInfo)

    fun deleteCity(commonInfo: CommonInfo)

    fun onDestroy()
}