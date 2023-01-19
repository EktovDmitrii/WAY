package weather.way.ui.start

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface StartView : MvpView {

    fun clearSearchField()

    fun startWeatherFragmentByName(cityName: String)

    fun startWeatherFragmentByGeo(lon: String, lat: String)
}