package weather.way.ui.start

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentStartBinding
import weather.way.domain.model.CommonInfo
import weather.way.ui.favourite.FavouriteFragment
import weather.way.ui.weather.WeatherFragment
import weather.way.utils.Constants.FRAGMENT_START_BINDING_NULL
import weather.way.utils.Constants.INVALID_GEO
import weather.way.utils.Constants.INVALID_NAME
import weather.way.utils.Constants.LOCATION_PERMISSION_CODE
import weather.way.utils.GeoLocationManager

class StartFragment : MvpAppCompatFragment(), StartView {
    private lateinit var locationManager: GeoLocationManager
    private var locationTrackingRequested = false
    private var lon: String? = null
    private var lat: String? = null

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_START_BINDING_NULL)

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
        initLocationManager()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnStartSearch.setOnClickListener {
            presenter.searchCityByName(binding.etCityNameSearch.text.toString())
        }
        binding.btnStartFavouriteFrag.setOnClickListener {
            launchFavouriteFragment()
        }
        binding.btnFindByLocation.setOnClickListener {
            val permissionGranted = ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
            if (permissionGranted) {
                locationManager.startLocationTracking(locationCallback)
                locationTrackingRequested = true
            } else {
                requestPermission()
            }
        }
    }

    private fun initLocationManager() {
        locationManager = GeoLocationManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clearSearchField() {
        binding.etCityNameSearch.text = null
    }

    override fun clearGeoData() {
        lon = null
        lat = null
    }

    override fun startWeatherFragment(commonInfo: CommonInfo) {
        launchCommonWeatherFragment(commonInfo)
    }

    override fun hideKeyBoard() {
        hideKeyboardFrom(requireContext(), view)
    }

    override fun errorName() {
        Toast.makeText(
            requireContext(),
            INVALID_NAME,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun errorGeo() {
        Toast.makeText(
            requireContext(),
            INVALID_GEO,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0 ?: return
            for (location in p0.locations) {
                lat = location.latitude.toString()
                lon = location.longitude.toString()
                locationManager.stopLocationTracking()
            }
            presenter.searchCityByGeo(lon, lat)
        }
    }

    private fun requestPermission(): Boolean {
        var permissionGranted = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val locationPermission = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            val locationPermissionGranted = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
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
        if (requestCode == LOCATION_PERMISSION_CODE) if (grantResults.size === 2 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.startLocationTracking(locationCallback)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun launchFavouriteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FavouriteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchCommonWeatherFragment(commonInfo: CommonInfo) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance(commonInfo))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() =
            StartFragment()
    }
}