package weather.way.ui

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import weather.way.domain.ApiRepository
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.AddCityToFavouriteUseCase
import weather.way.domain.useCases.GetForecastByNameUseCase
import weather.way.domain.useCases.GetHourlyForecastUseCase

@InjectViewState
class WeatherPresenterImpl(
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository
) : AbstractWeatherPresenter() {

    private val getHourlyForecastUseCase = GetHourlyForecastUseCase(apiRepository)
    private val getForecastByNameUseCase = GetForecastByNameUseCase(apiRepository)
    private val addCityToFavouriteUseCase = AddCityToFavouriteUseCase(daoRepository)
    private val compositeDisposable = CompositeDisposable()

    override fun getHourlyForecast(lon: String, lat: String) {
        val disposable = getHourlyForecastUseCase.getHourlyForecast(lon, lat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showHourlyForecast(it)
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "No data found")
            })
        compositeDisposable.add(disposable)
    }

    override fun getForecastByName(cityName: String) {
        val disposable = getForecastByNameUseCase.getForecastByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showHourlyForecast(it)
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "No data found")
            })
        compositeDisposable.add(disposable)
    }

    override fun addToFavourite(commonInfo: CommonInfo) {
        val disposable = addCityToFavouriteUseCase.addCityToFavourite(commonInfo)
            .subscribeOn(Schedulers.io())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe {
                viewState.addCityToFavouriteList(commonInfo)
                Log.d("ISINFAVOURITE", "${commonInfo.city.name}")
            }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}