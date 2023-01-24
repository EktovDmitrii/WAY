package weather.way.ui.start

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import weather.way.domain.ApiRepository
import weather.way.domain.useCases.GetForecastByNameUseCase
import weather.way.domain.useCases.GetHourlyForecastUseCase

@InjectViewState
class StartPresenterImpl(
    private val repository: ApiRepository
) : AbstractStartPresenter() {

    private val getForecastByNameUseCase = GetForecastByNameUseCase(repository)
    private val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)
    private val compositeDisposable = CompositeDisposable()

    override fun searchCityByName(cityName: String) {
        val disposable = getForecastByNameUseCase.getForecastByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideKeyBoard()
                viewState.startWeatherFragment(it)
                viewState.clearSearchField()
            }, {
                viewState.errorName()
            })
        compositeDisposable.add(disposable)
    }

    override fun searchCityByGeo(lon: String?, lat: String?) {
        val disposable = getHourlyForecastUseCase.getHourlyForecast(lon, lat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.startWeatherFragment(it)
                viewState.clearGeoData()
            }, {
                viewState.errorGeo()
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}