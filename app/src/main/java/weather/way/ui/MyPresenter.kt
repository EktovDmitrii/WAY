package weather.way.ui

interface MyPresenter {

    fun getHourlyForecast(lon: String, lat: String)

    fun onDestroy()
}