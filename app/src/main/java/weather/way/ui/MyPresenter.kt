package weather.way.ui

interface MyPresenter {

    fun getHourlyForecast(cityName: String)

    fun onDestroy()
}