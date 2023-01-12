package weather.way.ui.favourite

import weather.way.domain.model.CommonInfo

interface FavouritePresenter {

    fun getWeatherList(commonInfo: CommonInfo)

    fun onDestroy()
}