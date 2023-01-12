package weather.way.ui.favourite

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import weather.way.domain.DaoRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.GetFavouriteListUseCase

@InjectViewState
class FavouritePresenterImpl(
    private val repository: DaoRepository
) : AbstractFavouritePresenter() {

    private val getFavouriteUseCase = GetFavouriteListUseCase(repository)
    private val compositeDisposable = CompositeDisposable()

    override fun getWeatherList(commonInfo: CommonInfo) {
        val disposable = getFavouriteUseCase.getFavouriteList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showFavouriteList(commonInfo)
            }, {
                throw RuntimeException("no value")
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}