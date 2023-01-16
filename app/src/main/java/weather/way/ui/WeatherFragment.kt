package weather.way.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentWeatherBinding
import weather.way.domain.model.CommonInfo
import weather.way.ui.favourite.FavouriteFragment
import weather.way.utils.Constants
import weather.way.utils.Constants.CELSIUS
import weather.way.utils.Constants.CITY_NAME
import weather.way.utils.Constants.EMPTY_STRING
import weather.way.utils.Constants.LAT
import weather.way.utils.Constants.LON
import weather.way.utils.convertFahrenheitToCelsius
import weather.way.utils.convertTimestampToTime

class WeatherFragment : MvpAppCompatFragment(), WeatherView {

    private var adapter: WeatherPerHourAdapter? = null

    @InjectPresenter
    lateinit var presenter: AbstractWeatherPresenter

    @ProvidePresenter
    fun provide(): AbstractWeatherPresenter = get()

    private var _binding: FragmentWeatherBinding? = null
    val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException(Constants.FRAGMENT_WEATHER_BINDING_NULL)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getForecastByName(getCityName())
        presenter.getHourlyForecast(getCityLon(), getCityLat())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    private fun setAdapter() {
        adapter = WeatherPerHourAdapter()
        binding.rvWeatherPerHour.adapter = adapter
    }

    private fun getCityLon(): String {
        return requireArguments().getString(LON, EMPTY_STRING)
    }

    private fun getCityLat(): String {
        return requireArguments().getString(LAT, EMPTY_STRING)
    }

    private fun getCityName(): String {
        return requireArguments().getString(CITY_NAME, EMPTY_STRING)
    }

    override fun addCityToFavouriteList(commonInfo: CommonInfo) {
        binding.btnAddToFavourite.visibility = View.VISIBLE
    }

    override fun showHourlyForecast(commonInfo: CommonInfo) {
        binding.tvCityName.text = commonInfo.city.name
        binding.tvSunriseValue.text = convertTimestampToTime(commonInfo.city.sunrise)
        binding.tvSunsetValue.text = convertTimestampToTime(commonInfo.city.sunset)
        with(commonInfo.list[2].main) {
            binding.tvCurrentTemp.text = convertFahrenheitToCelsius(temp).toString() + CELSIUS
            binding.tvFeelsLikeValue.text =
                convertFahrenheitToCelsius(feels_like).toString() + CELSIUS
        }
        setAdapter()
        adapter?.myData = commonInfo.list
        adapter?.submitList(commonInfo.list)
        binding.btnAddToFavourite.setOnClickListener {
            presenter.addToFavourite(commonInfo)
        }
        binding.btnGoToFav.setOnClickListener {
            launchFavouriteFragment()
        }
        binding.tvCityName.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun launchFavouriteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FavouriteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(lon: String, lat: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(LON, lon)
                }.apply {
                    putString(LAT, lat)
                }
            }

        fun newInstance2(cityName: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(CITY_NAME, cityName)
                }
            }
    }
}