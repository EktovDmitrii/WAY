package weather.way.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import weather.way.databinding.FragmentFavouriteBinding
import weather.way.domain.model.CommonInfo
import weather.way.utils.Constants
import weather.way.utils.Constants.CITY_NAME

class FavouriteFragment : MvpAppCompatFragment(), FavouriteView {


    private var adapter: FavouriteAdapter? = null

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
//        Log.d("Cheremsha", "${parseArgs().toString()}")
        presenter.getWeatherList(parseArgs())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    private fun parseArgs(): CommonInfo {
        return requireArguments().getSerializable(CITY_NAME) as CommonInfo
    }

    override fun showFavouriteList(commonInfo: CommonInfo) {
        setAdapter()
        adapter?.myData = listOf(commonInfo)
        adapter?.submitList(listOf(commonInfo))
    }

    private fun setAdapter() {
        adapter = FavouriteAdapter()
        binding.rvFavouriteCities.adapter = adapter
    }

    companion object {

        fun newInstance(commonInfo: CommonInfo) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CITY_NAME, commonInfo)
                }
            }

    }
}