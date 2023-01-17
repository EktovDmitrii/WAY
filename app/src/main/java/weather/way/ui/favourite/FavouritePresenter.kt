package weather.way.ui.favourite

import weather.way.domain.model.CommonInfo

interface FavouritePresenter {

    fun getWeatherList()

    fun deleteCity(commonInfo: CommonInfo)

    fun onDestroy()
}