package weather.way.ui.start

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import weather.way.domain.model.CommonInfo

@StateStrategyType(SingleStateStrategy::class)
interface StartView : MvpView {

    fun clearSearchField()

    fun clearGeoData()

    fun errorName()

    fun errorGeo()

    fun hideKeyBoard()

    fun startWeatherFragment(commonInfo: CommonInfo)

}