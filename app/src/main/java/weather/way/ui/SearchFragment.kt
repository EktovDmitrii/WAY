package weather.way.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.get
import weather.way.utils.Constants.FRAGMENT_SEARCH_BINDING_NULL
import weather.way.R
import weather.way.databinding.FragmentSearchBinding
import weather.way.domain.ApiRepository
import weather.way.domain.model.CommonInfo
import weather.way.domain.useCases.GetHourlyForecastUseCase

class SearchFragment : MvpAppCompatFragment() {


    val compositeDisposable = CompositeDisposable()
    val repository = get<ApiRepository>()
    val getHourlyForecastUseCase = GetHourlyForecastUseCase(repository)

    private var _binding: FragmentSearchBinding? = null
    val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_SEARCH_BINDING_NULL)

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
            searchWeather()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.dispose()
    }

    private fun searchWeather() {
        val disposable = getHourlyForecastUseCase.getHourlyForecast()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("WEATHER_CHECK", it.toString())
                launchWeatherFragment(it)
            }, {
                Log.d("WEATHER_CHECK", "Ебаный сука нахуй нет данных")
            })
        compositeDisposable.add(disposable)
    }

    private fun launchWeatherFragment(commonInfo: CommonInfo) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance(commonInfo))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() =
            SearchFragment()
    }
}