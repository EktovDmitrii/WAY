package weather.way.ui

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import weather.way.domain.model.CommonInfo

@StateStrategyType(SingleStateStrategy::class)
interface MyView: MvpView {

fun showWeatherInCity(commonInfo: CommonInfo)

}