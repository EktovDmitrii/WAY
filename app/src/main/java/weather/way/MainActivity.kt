package weather.way

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get
import org.koin.java.KoinJavaComponent.get
import weather.way.data.network.ApiFactory
import weather.way.domain.ApiRepository
import weather.way.domain.useCases.GetWeatherInCityUseCase

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    val repository = get<ApiRepository>()
    val getWeatherInCityUseCase = GetWeatherInCityUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val disposable = getWeatherInCityUseCase.getWeatherInCity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "No data found")
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}