package weather.way.ui

interface MyPresenter {

    fun getWeatherInCity(cityName: String)

    fun onDestroy()
}