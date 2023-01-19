package weather.way.ui.start

import android.util.Log
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
                viewState.startWeatherFragmentByName(it.city.name)
                viewState.clearSearchField()
            }, {
            })
        compositeDisposable.add(disposable)
    }

    override fun searchCityByGeo(lon: String, lat: String) {
        val disposable = getHourlyForecastUseCase.getHourlyForecast(lon, lat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.startWeatherFragmentByGeo(
                    it.city.coord.lon.toString(),
                    it.city.coord.lat.toString()
                )
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "Ебаный сука нахуй нет данных")
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}