package weather.way.ui.favourite

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import weather.way.R
import weather.way.databinding.FavouriteSmallCardBinding
import weather.way.domain.model.CommonInfo
import weather.way.domain.model.HourlyForecast
import weather.way.utils.Constants
import weather.way.utils.Constants.CELSIUS
import weather.way.utils.convertFahrenheitToCelsius

class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FavouriteSmallCardBinding = FavouriteSmallCardBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(commonInfo: CommonInfo) {
        binding.tvCityTemp.text =
            convertFahrenheitToCelsius(commonInfo.list[1].main.temp).toString() + CELSIUS
        binding.tvCityNameInFav.text = commonInfo.city.name
        setRightImage(commonInfo)
    }

    private fun setRightImage(commonInfo: CommonInfo) {
        if (commonInfo.list[0].weather[0].main == Constants.CLOUDS) {
            Glide.with(itemView).load(R.drawable.ic_clouds_icon)
                .into(binding.ivWeatherIcon)
        }
        if (commonInfo.list[0].weather[0].main == Constants.RAIN) {
            Glide.with(itemView).load(R.drawable.ic_rain_icon)
                .into(binding.ivWeatherIcon)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SNOW) {
            Glide.with(itemView).load(R.drawable.ic_snow)
                .into(binding.ivWeatherIcon)
        }
        if (commonInfo.list[0].weather[0].main == Constants.SUN) {
            Glide.with(itemView).load(R.drawable.ic_sun_icon)
                .into(binding.ivWeatherIcon)
        }
    }
}