package weather.way.ui.favourite

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import weather.way.domain.model.CommonInfo

@StateStrategyType(SingleStateStrategy::class)
interface FavouriteView : MvpView {

    fun showFavouriteList(commonInfo: List<CommonInfo>)

    fun updateAllData(commonInfo: CommonInfo)

    fun deleteCity(commonInfo: CommonInfo)
}