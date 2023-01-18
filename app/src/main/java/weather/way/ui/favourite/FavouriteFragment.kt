package weather.way.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.R
import weather.way.databinding.FragmentFavouriteBinding
import weather.way.domain.model.CommonInfo
import weather.way.ui.weather.WeatherFragment
import weather.way.utils.Constants
import weather.way.utils.Constants.CITY_NAME

class FavouriteFragment : MvpAppCompatFragment(), FavouriteView {


    private lateinit var adapter: FavouriteAdapter

    @InjectPresenter
    lateinit var presenter: AbstractFavouritePresenter

    @ProvidePresenter
    fun provide(): AbstractFavouritePresenter = get()

    private var _binding: FragmentFavouriteBinding? = null
    val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException(Constants.FRAGMENT_WEATHER_BINDING_NULL)


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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showFavouriteList(commonInfo: List<CommonInfo>) {
        setAdapter()
        adapter.myData = commonInfo
        adapter.submitList(commonInfo)
    }

    override fun deleteCity(commonInfo: CommonInfo) {
        commonInfo.isInFavourite = false
    }

    private fun setAdapter() {
        adapter = FavouriteAdapter(
            onItemClickListenerFavorites = {
               launchWeatherFragmentByName(it.city.name)
            },
            onItemClickDelete = {
                presenter.deleteCity(it)
            }
        )
        binding.rvFavouriteCities.adapter = adapter
//        setupSwipeListener(binding.rvFavouriteCities)
    }

    private fun setupSwipeListener(rvFavouriteMovie: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                presenter.deleteCity(item)
                item.isInFavourite = false
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvFavouriteMovie)
    }

    private fun launchWeatherFragmentByName(cityName: String) {
        Log.d("start_Fragment", "Fragment launched")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, WeatherFragment.newInstance2(cityName))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() =
            FavouriteFragment()

    }
}