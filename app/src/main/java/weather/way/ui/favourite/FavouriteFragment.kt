package weather.way.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentFavouriteBinding
import weather.way.domain.model.CommonInfo
import weather.way.ui.start.StartFragment
import weather.way.ui.weather.WeatherFragment
import weather.way.utils.Constants
import weather.way.utils.Constants.EMPTY_FAVOURITE

class FavouriteFragment : MvpAppCompatFragment(), FavouriteView {

    private lateinit var adapter: FavouriteAdapter

    @InjectPresenter
    lateinit var presenter: AbstractFavouritePresenter

    @ProvidePresenter
    fun provide(): AbstractFavouritePresenter = get()

    private var _binding: FragmentFavouriteBinding? = null
    val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException(Constants.FRAGMENT_FAVOURITE_BINDING_NULL)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getWeatherList()
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun updateAllData(commonInfo: CommonInfo) {
        presenter.getForecastByName(commonInfo.city.name)
    }

    override fun showError() {
        Toast.makeText(requireContext(), EMPTY_FAVOURITE, Toast.LENGTH_SHORT).show()
    }

    override fun showFavouriteList(commonInfo: List<CommonInfo>) {
        adapter.myData = commonInfo
        adapter.submitList(commonInfo)
        setComponentsVisibility()
        binding.btnAddCity.setOnClickListener {
            launchStartFragment()
        }
    }

    private fun setComponentsVisibility() {
        binding.tvFavouriteTitle.visibility = View.VISIBLE
        binding.btnAddCity.visibility = View.VISIBLE
        binding.favouriteProgressBar.visibility = View.GONE
    }

    override fun deleteCity(commonInfo: CommonInfo) {
        commonInfo.isInFavourite = false
    }

    private fun setAdapter() {
        adapter = FavouriteAdapter(
            onItemClickListenerFavorites = {
                launchWeatherFragmentByName(it)
            },
            onItemClickDelete = {
                presenter.deleteCity(it)
            }
        )
        binding.rvFavouriteCities.adapter = adapter
    }

    private fun launchWeatherFragmentByName(commonInfo: CommonInfo) {
        Log.d("start_Fragment", "Fragment launched")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance(commonInfo))
            .addToBackStack(null)
            .commit()
    }

    private fun launchStartFragment() {
        Log.d("start_Fragment", "Fragment launched")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, StartFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() =
            FavouriteFragment()

    }
}