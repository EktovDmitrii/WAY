package weather.way.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.Constants
import weather.way.Constants.PATH_PARAM_CITY_NAME
import weather.way.databinding.FragmentWeatherBinding
import weather.way.domain.model.CommonInfo

class WeatherFragment : MvpAppCompatFragment(), MyView {

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
presenter.viewState.showWeatherInCity(weather)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWeatherInfo(): CommonInfo {
        return requireArguments().getSerializable(PATH_PARAM_CITY_NAME) as CommonInfo
    }

    override fun showWeatherInCity(commonInfo: CommonInfo) {
            binding.tvCityName.text = commonInfo.name
            binding.tvCurrentTemp.text = commonInfo.main.temp.toString()
            binding.tvMaxDayTemp.text = commonInfo.main.temp_max.toString()
            binding.tvMinDayTemp.text = commonInfo.main.temp_min.toString()

    }

    private fun setAllBinds(weather: CommonInfo) {
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