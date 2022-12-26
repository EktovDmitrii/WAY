package weather.way.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import weather.way.Constants
import weather.way.Constants.PATH_PARAM_CITY_NAME
import weather.way.R
import weather.way.databinding.FragmentSearchBinding
import weather.way.databinding.FragmentWeatherBinding
import weather.way.domain.model.CommonInfo

class WeatherFragment : Fragment() {

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
        setAllBinds()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAllBinds(weather: CommonInfo) {
        with(weather) {
            binding.tvCityName.text = name
            binding.tvCurrentTemp.text = main.temp.toString()
            binding.tvMaxDayTemp.text = main.temp_max.toString()
            binding.tvMinDayTemp.text = main.temp_min.toString()

        }
    }

    companion object {
        fun newInstance(cityName: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(PATH_PARAM_CITY_NAME, cityName)
                }
            }
    }
}