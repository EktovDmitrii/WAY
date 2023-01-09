package weather.way.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.GetHourlyForecastUseCase
import weather.way.utils.GeoLocationManager

class MainActivity : AppCompatActivity() {
    private lateinit var locationManager: GeoLocationManager
    private var locationTrackingRequested = false
    private val LOCATION_PERMISSION_CODE = 1000
    private var lon: String = ""
    private var lat: String = ""


    val compositeDisposable = CompositeDisposable()
    val repository = get<ApiRepository>()
    val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0 ?: return
            for (location in p0.locations) {
                lat = location.latitude.toString()
                lon = location.longitude.toString()
                Log.d("MainActivityy", "$lat $lon")
                searchWeather(lon, lat)
                locationManager.stopLocationTracking()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = GeoLocationManager(this)
        val permissionGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            locationManager.startLocationTracking(locationCallback)
            locationTrackingRequested = true
        } else {
            requestPermission()
        }
    }

    private fun requestPermission(): Boolean {
        var permissionGranted = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val locationPermission = arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            val locationPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
            if (locationPermissionGranted) {
                requestPermissions(locationPermission, LOCATION_PERMISSION_CODE)
            } else
                permissionGranted = true
        }
        return permissionGranted
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.size === 2 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.startLocationTracking(locationCallback)
            } else {
                Log.d("MainActivityy", "Permission denied")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPause() {
        super.onPause()
        locationManager.stopLocationTracking()
        compositeDisposable.dispose()
    }

    override fun onResume() {
        super.onResume()
        if (locationTrackingRequested) {
            locationManager.startLocationTracking(locationCallback)
        }
    }

    private fun searchWeather(lon: String, lat: String) {
        val disposable = getHourlyForecastUseCase.getHourlyForecast(lon, lat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("WEATHER_CHECK", it.toString())
                launchWeatherFragment(lon, lat)
            }, {
                Log.d("WEATHER_CHECK", "Ебаный сука нахуй нет данных")
            })
        compositeDisposable.add(disposable)
    }

    private fun launchWeatherFragment(lon: String, lat: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance(lon, lat))
            .addToBackStack(null)
            .commit()
    }
}