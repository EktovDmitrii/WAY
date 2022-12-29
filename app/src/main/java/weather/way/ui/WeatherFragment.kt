package weather.way.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.utils.Constants
import weather.way.utils.Constants.PATH_PARAM_CITY_NAME
import weather.way.databinding.FragmentWeatherBinding
import weather.way.domain.model.CommonInfo
import weather.way.utils.convertFahrenheitToCelsius

class WeatherFragment : MvpAppCompatFragment(), MyView {

    private var adapter: WeatherPerHourAdapter? = null

    @InjectPresenter
    lateinit var presenter: AbstractFragmentPresenter

    @ProvidePresenter
    fun provide(): AbstractFragmentPresenter = get()

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
        val weather = getWeatherInfo()
        presenter.viewState.showHourlyForecast(weather)
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

    private fun getWeatherInfo(): CommonInfo {
        return requireArguments().getSerializable(PATH_PARAM_CITY_NAME) as CommonInfo
    }

    override fun showHourlyForecast(commonInfo: CommonInfo) {
        binding.tvCityName.text = commonInfo.city.name
        with(commonInfo.list[2].main) {
            binding.tvCurrentTemp.text = convertFahrenheitToCelsius(temp).toString()
            binding.tvMaxDayTemp.text = convertFahrenheitToCelsius(temp_max).toString()
            binding.tvMinDayTemp.text = convertFahrenheitToCelsius(temp_min).toString()
        }
        setAdapter()
        adapter?.myData = commonInfo.list
        adapter?.submitList(commonInfo.list)

    }



    companion object {
        fun newInstance(commonInfo: CommonInfo) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PATH_PARAM_CITY_NAME, commonInfo)
                }
            }
    }
}