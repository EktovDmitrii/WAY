package weather.way.ui

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import weather.way.domain.ApiRepository
import weather.way.domain.useCases.GetHourlyForecastUseCase

@InjectViewState
class MyPresenterImpl(
    private val repository: ApiRepository,
) : AbstractFragmentPresenter() {

    private val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)

    val compositeDisposable = CompositeDisposable()

    override fun getHourlyForecast(cityName: String) {
        val disposable = getHourlyForecastUseCase.getHourlyForecast(cityName)
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

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}