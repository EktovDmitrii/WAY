package weather.way.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.matteobattilana.weather.PrecipType
import kotlinx.android.synthetic.main.fragment_weather.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentWeatherBinding
import weather.way.databinding.FragmentWeatherNameBinding
import weather.way.domain.model.CommonInfo
import weather.way.ui.favourite.FavouriteFragment
import weather.way.ui.weather.AbstractWeatherPresenter
import weather.way.ui.weather.WeatherPerHourAdapter
import weather.way.ui.weather.WeatherView
import weather.way.utils.Constants
import weather.way.utils.convertFahrenheitToCelsius
import weather.way.utils.convertTimestampToTime

class WeatherNameFragment : MvpAppCompatFragment(), WeatherView {

    private var _binding: FragmentWeatherNameBinding? = null
    val binding: FragmentWeatherNameBinding
        get() = _binding ?: throw RuntimeException(Constants.FRAGMENT_WEATHER_NAME_BINDING_NULL)

    private var adapter: WeatherPerHourAdapter? = null

    @InjectPresenter
    lateinit var presenter: AbstractWeatherPresenter

    @ProvidePresenter
    fun provide(): AbstractWeatherPresenter = get()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getForecastByName(getCityName())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    override fun showHourlyForecast(commonInfo: CommonInfo) {
        setWeatherBackground(commonInfo)
        setFavouriteClickListener(commonInfo)
        setFavouriteButton(commonInfo)
        setBackground(commonInfo)
        setComponentsVisibility()
        binding.tvCityName.text = commonInfo.city.name
        binding.tvSunriseValue.text = convertTimestampToTime(commonInfo.city.sunrise)
        binding.tvSunsetValue.text = convertTimestampToTime(commonInfo.city.sunset)
        with(commonInfo.list[2].main) {
            binding.tvCurrentTemp.text = convertFahrenheitToCelsius(temp).toString() + Constants.CELSIUS
            binding.tvFeelsLikeValue.text =
                convertFahrenheitToCelsius(feels_like).toString() + Constants.CELSIUS
        }
        setAdapter()
        adapter?.myData = commonInfo.list
        adapter?.submitList(commonInfo.list)
        binding.btnGoToFav.setOnClickListener {
            launchFavouriteFragment()
        }}

    override fun addCityToFavouriteList(commonInfo: CommonInfo) {
        binding.btnAddToFavourite.visibility = View.VISIBLE
    }

    private fun setAdapter() {
        adapter = WeatherPerHourAdapter()
        binding.rvWeatherPerHour.adapter = adapter
    }


    private fun setComponentsVisibility() {
        binding.btnGoToFav.visibility = View.VISIBLE
        binding.tvSunriseTitle.visibility = View.VISIBLE
        binding.tvSunsetTitle.visibility = View.VISIBLE
        binding.btnAddToFavourite.visibility = View.VISIBLE
//        binding.btnSettings.visibility = View.VISIBLE
        binding.weatherProgressBar.visibility = View.GONE
    }

    private fun setFavouriteButton(commonInfo: CommonInfo) {
        if (commonInfo.isInFavourite) {
            Glide.with(this).load(R.drawable.ic_in_favourite)
                .into(binding.btnAddToFavourite)
        }
    }

    private fun setWeatherBackground(commonInfo: CommonInfo) {
        if (commonInfo.list[0].weather[0].main == Constants.RAIN) {
            weather_view.setWeatherData(PrecipType.RAIN)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SNOW) {
            weather_view.setWeatherData(PrecipType.SNOW)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SUN) {
            weather_view.setWeatherData(PrecipType.CLEAR)
        }
    }

    private fun setBackground(commonInfo: CommonInfo) {
        if (commonInfo.list[0].weather[0].main == Constants.RAIN) {
            binding.scrollView.background = resources.getDrawable(R.mipmap.ic_rain_again_foreground)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SNOW) {
            binding.scrollView.background = resources.getDrawable(R.drawable.snow_again)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SUN) {
            binding.scrollView.background = resources.getDrawable(R.drawable.sunny)
        }
        if (commonInfo.list[0].weather[0].main == Constants.CLOUDS) {
            binding.scrollView.background = resources.getDrawable(R.drawable.cloudy)
        }
    }

    private fun setFavouriteClickListener(commonInfo: CommonInfo) {
        Log.d("IS_IN_FAVOURITE", "${commonInfo.isInFavourite}")
        binding.btnAddToFavourite.setOnClickListener {
            if (!commonInfo.isInFavourite) {
                presenter.addToFavourite(commonInfo)
                Glide.with(this).load(R.drawable.ic_in_favourite)
                    .into(binding.btnAddToFavourite)
            } else {
                Toast.makeText(requireContext(), "Already added", Toast.LENGTH_SHORT).show()
            }
            commonInfo.isInFavourite = true
            Log.d("IS_IN_FAVOURITE", "after change: ${commonInfo.isInFavourite}")

        }
    }
    private fun getCityName(): String {
        return requireArguments().getString(Constants.CITY_NAME, Constants.EMPTY_STRING)
    }

    private fun launchFavouriteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FavouriteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            WeatherNameFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}