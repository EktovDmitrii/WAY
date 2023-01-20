package weather.way.ui.favourite

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerobranch.layout.SwipeLayout
import weather.way.R
import weather.way.databinding.FavouriteSmallCardBinding
import weather.way.domain.model.CommonInfo
import weather.way.domain.model.HourlyForecast
import weather.way.utils.Constants
import weather.way.utils.Constants.CELSIUS
import weather.way.utils.convertFahrenheitToCelsius
import weather.way.utils.convertTimestampToTime

class FavouriteViewHolder(
    itemView: View,
    private val onItemClickListenerFavorites: (CommonInfo) -> Unit,
    private val onItemClickDelete: (CommonInfo) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FavouriteSmallCardBinding = FavouriteSmallCardBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(commonInfo: CommonInfo) {
        binding.tvCityTemp.text =
            convertFahrenheitToCelsius(commonInfo.list[0].main.temp).toString() + CELSIUS
        binding.tvCityNameInFav.text = commonInfo.city.name
        binding.timeTest.text = convertTimestampToTime(commonInfo.list[0].dt)
        setRightImage(commonInfo)

        binding.rightView.setOnClickListener(View.OnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClickDelete(commonInfo)
            }
        })

        binding.swipeLayout.setOnActionsListener(
            object : SwipeLayout.SwipeActionsListener {
                override fun onOpen(direction: Int, isContinuous: Boolean) {
                    binding.rightView.visibility = View.VISIBLE
                    if (direction == SwipeLayout.LEFT && isContinuous) {
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                        }
                    }
                }

                override fun onClose() {
                    binding.rightView.visibility = View.INVISIBLE

                }

            })

        binding.dragItem.setOnClickListener {
            onItemClickListenerFavorites(commonInfo)
        }

    }


private fun setRightImage(commonInfo: CommonInfo) {
    if (commonInfo.list[0].weather[0].main == Constants.CLOUDS) {
        Glide.with(itemView).load(R.drawable.ic_cloud_vector)
            .into(binding.ivWeatherIcon)
    }
    if (commonInfo.list[0].weather[0].main == Constants.RAIN) {
        Glide.with(itemView).load(R.drawable.ic_rain_vector)
            .into(binding.ivWeatherIcon)
    }
    if (commonInfo.list[0].weather[0].main == Constants.SNOW) {
        Glide.with(itemView).load(R.drawable.ic_snow_vector)
            .into(binding.ivWeatherIcon)
    }
    if (commonInfo.list[0].weather[0].main == Constants.SUN) {
        Glide.with(itemView).load(R.drawable.ic_sun_vector)
            .into(binding.ivWeatherIcon)
    }
}
}