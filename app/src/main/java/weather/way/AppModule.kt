package weather.way

import org.koin.dsl.module
import weather.way.data.common.network.ApiFactory
import weather.way.data.common.network.RepositoryImpl
import weather.way.domain.ApiRepository
import weather.way.ui.AbstractFragmentPresenter
import weather.way.ui.MyPresenterImpl

val appModule = module {
    single {
        ApiFactory.apiService
    }
    factory<ApiRepository> {
        RepositoryImpl(get())
    }
    factory<AbstractFragmentPresenter> {
        MyPresenterImpl(
            get()
        )
    }
}