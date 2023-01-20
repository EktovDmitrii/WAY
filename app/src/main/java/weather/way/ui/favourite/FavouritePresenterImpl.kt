package weather.way.ui.favourite

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.consumesAll
import moxy.InjectViewState
import weather.way.domain.ApiRepository
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.*

@InjectViewState
class FavouritePresenterImpl(
    private val daoRepository: DaoRepository,
    private val apiRepository: ApiRepository
) : AbstractFavouritePresenter() {

    private val getFavouriteUseCase = GetFavouriteListUseCase(daoRepository)
    private val deleteCityFromFavouriteUseCase = DeleteCityFromFavouriteUseCase(daoRepository)
    private val getForecastByNameUseCase = GetForecastByNameUseCase(apiRepository)
    private val updateDataUseCase = UpdateDataUseCase(daoRepository)
    private val compositeDisposable = CompositeDisposable()

    override fun getWeatherList() {
        val disposable = getFavouriteUseCase.getFavouriteList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (city in it) {
                    viewState.updateAllData(city)
                }
                viewState.showFavouriteList(it)
            }, {
                throw RuntimeException("no value")
            })
        compositeDisposable.add(disposable)
    }

    override fun deleteCity(commonInfo: CommonInfo) {
        val disposable = deleteCityFromFavouriteUseCase.deleteCity(commonInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.deleteCity(commonInfo)
                getWeatherList()
            }
        compositeDisposable.add(disposable)
    }

    override fun getForecastByName(cityName: String) {
        val disposable = getForecastByNameUseCase.getForecastByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateData(it)
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "No data found")
            })
        compositeDisposable.add(disposable)
    }

    override fun updateData(commonInfo: CommonInfo) {
        val disposable = updateDataUseCase.updateWeatherData(commonInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                getForecastByName(commonInfo.city.name)
            }, {
                throw RuntimeException("no value")
            })
        compositeDisposable.add(disposable)
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}