package weather.way.ui

import weather.way.domain.model.HourlyForecast

interface MyPresenter {

    fun getHourlyForecast()

    fun onDestroy()
}