package weather.way

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import weather.way.data.network.ApiFactory
import weather.way.data.network.ApiService

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val disposable = ApiFactory.apiService.getCurrentWeatherInCity(
            "London",
            "d5242d25373507cc264933041d3306df"
        )
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