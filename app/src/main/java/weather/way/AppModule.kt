package weather.way

import org.koin.dsl.module
import weather.way.data.network.ApiFactory
import weather.way.data.network.RepositoryImpl
import weather.way.domain.ApiRepository

val appModule = module {
    single {
        ApiFactory.apiService
    }
    factory<ApiRepository> {
        RepositoryImpl(get())
    }
}