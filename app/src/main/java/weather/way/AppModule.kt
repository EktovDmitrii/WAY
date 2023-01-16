package weather.way

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import weather.way.data.common.dataBase.AppDatabase
import weather.way.data.common.dataBase.DaoRepositoryImpl
import weather.way.data.common.dataBase.WeatherDao
import weather.way.data.common.network.ApiFactory
import weather.way.data.common.network.RepositoryImpl
import weather.way.domain.ApiRepository
import weather.way.domain.DaoRepository
import weather.way.ui.AbstractWeatherPresenter
import weather.way.ui.WeatherPresenterImpl
import weather.way.ui.favourite.AbstractFavouritePresenter
import weather.way.ui.favourite.FavouritePresenterImpl
import weather.way.ui.start.AbstractStartPresenter
import weather.way.ui.start.StartPresenterImpl

val appModule = module {
    single {
        ApiFactory.apiService
    }
    factory<ApiRepository> {
        RepositoryImpl(get())
    }
    factory<DaoRepository> {
        DaoRepositoryImpl(get())
    }

    single {
        Room
            .databaseBuilder(
                androidContext(), AppDatabase::class.java, AppDatabase.DB_NAME
            ).build()
    }

    single<WeatherDao> {
        get<AppDatabase>().weatherDao()
    }

    factory<AbstractWeatherPresenter> {
        WeatherPresenterImpl(
            get(),
            get()
        )
    }
    factory<AbstractFavouritePresenter> {
        FavouritePresenterImpl(get())
    }

    factory<AbstractStartPresenter> {
        StartPresenterImpl(get())
    }

}