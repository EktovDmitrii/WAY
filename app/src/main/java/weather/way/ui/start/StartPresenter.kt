package weather.way.ui.start

interface StartPresenter {

    fun searchCityByName(cityName: String)

    fun searchCityByGeo(lon: String, lat: String)

    fun onDestroy()
}