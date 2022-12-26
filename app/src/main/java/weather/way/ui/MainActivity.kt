package weather.way.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.domain.ApiRepository
import weather.way.domain.useCases.GetWeatherInCityUseCase

class MainActivity : AppCompatActivity() {

private val searchFragment = SearchFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment(searchFragment)
    }

    private fun launchFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment_container_view, fragment)
        addToBackStack(null)
        .commit()
    }
    }
}