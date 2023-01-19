package weather.way.ui.start

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentStartBinding
import weather.way.ui.favourite.FavouriteFragment
import weather.way.ui.weather.WeatherFragment
import weather.way.utils.Constants.FRAGMENT_WEATHER_BINDING_NULL
import weather.way.utils.GeoLocationManager

class StartFragment : MvpAppCompatFragment(), StartView{
    private lateinit var locationManager: GeoLocationManager
    private var locationTrackingRequested = false
    private val LOCATION_PERMISSION_CODE = 1000
    private var lon: String = ""
    private var lat: String = ""

    private var _binding: FragmentStartBinding? = null
    val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_WEATHER_BINDING_NULL)

    @InjectPresenter
    lateinit var presenter: AbstractStartPresenter

    @ProvidePresenter
    fun provide(): AbstractStartPresenter = get()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartSearch.setOnClickListener {
            locationManager = GeoLocationManager(requireContext())
            presenter.searchCityByName(binding.etCityNameSearch.text.toString())
        }
        binding.btnStartFavouriteFrag.setOnClickListener {
            locationManager = GeoLocationManager(requireContext())
            launchFavouriteFragment()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.btnFindByLocation.setOnClickListener {
//            launchWeatherFragmentByGeo(lon, lat)

            Log.d("MainActivityy", "$lat $lon")

            locationManager = GeoLocationManager(requireContext())
            val permissionGranted = ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED

            if (permissionGranted) {
                locationManager.startLocationTracking(locationCallback)
                locationTrackingRequested = true
            } else {
                requestPermission()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        locationManager.stopLocationTracking()
//        requireActivity().supportFragmentManager.tran
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun clearSearchField() {
        binding.etCityNameSearch.text = null
    }

    override fun startWeatherFragmentByName(cityName: String) {
        launchWeatherFragmentByName(cityName)
    }

    override fun startWeatherFragmentByGeo(lon: String, lat: String) {
//        launchWeatherFragmentByGeo(lon, lat)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0 ?: return
            for (location in p0.locations) {
                lat = location.latitude.toString()
                lon = location.longitude.toString()
//                Log.d("MainActivityy", "$lat $lon")
//                launchWeatherFragmentByGeo(lon, lat)
                locationManager.stopLocationTracking()
            }
            presenter.searchCityByGeo(lon, lat)
            launchWeatherFragmentByGeo(lon, lat)

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
                requireContext(),
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
                Log.d("MainActivity", "Permission denied")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

//    override fun onResume() {
//        super.onResume()
//        if (locationTrackingRequested) {
//            locationManager.stopLocationTracking()
//        }
//    }

    private fun launchWeatherFragmentByName(cityName: String) {
        Log.d("start_Fragment", "Fragment launched")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance2(cityName))
            .addToBackStack(null)
            .commit()
    }
    private fun launchFavouriteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FavouriteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchWeatherFragmentByGeo(lon: String, lat: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance(lon, lat))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() =
            StartFragment()
    }
}