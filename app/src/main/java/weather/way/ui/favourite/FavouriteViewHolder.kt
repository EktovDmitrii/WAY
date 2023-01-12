package weather.way.ui.favourite

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import weather.way.databinding.FavouriteSmallCardBinding
import weather.way.domain.model.CommonInfo
import weather.way.utils.convertFahrenheitToCelsius

class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FavouriteSmallCardBinding = FavouriteSmallCardBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(commonInfo: CommonInfo) {
        binding.tvCityTemp.text =
            convertFahrenheitToCelsius(commonInfo.list[1].main.temp).toString()
        binding.tvCityNameInFav.text = commonInfo.city.name
    }
}