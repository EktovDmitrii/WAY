package weather.way.ui

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.GetWeatherInCityUseCase

@InjectViewState
class MyPresenterImpl(
    private val repository: ApiRepository,
) : AbstractFragmentPresenter() {

    private val getWeatherInCityUseCase = GetWeatherInCityUseCase(repository)
    val compositeDisposable = CompositeDisposable()


    override fun getWeatherInCity(cityName: String) {

        val disposable = getWeatherInCityUseCase.getWeatherInCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showWeatherInCity(it)

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