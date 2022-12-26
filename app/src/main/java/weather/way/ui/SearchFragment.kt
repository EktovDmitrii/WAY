package weather.way.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.get
import weather.way.Constants.FRAGMENT_SEARCH_BINDING_NULL
import weather.way.databinding.FragmentSearchBinding
import weather.way.domain.ApiRepository
import weather.way.domain.useCases.GetWeatherInCityUseCase

class SearchFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()
    val repository = get<ApiRepository>()
    val getWeatherInCityUseCase = GetWeatherInCityUseCase(repository)

    private var _binding: FragmentSearchBinding? = null
    val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_SEARCH_BINDING_NULL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener {
            val cityName = binding.etSearchCity.text.toString()
            searchWeather(cityName)
        }

    }

    private fun searchWeather(cityName: String) {
        val disposable = getWeatherInCityUseCase.getWeatherInCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("WEATHER_CHECK", it.toString())
            }, {
                Log.d("WEATHER_CHECK", "No data found")
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.dispose()
    }

    companion object {

        fun newInstance() =
            SearchFragment()
    }
}