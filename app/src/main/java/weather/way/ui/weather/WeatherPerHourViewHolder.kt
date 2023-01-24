package weather.way.ui.weather

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import weather.way.R
import weather.way.databinding.WeatherPerHourCardBinding
import weather.way.domain.model.HourlyForecast
import weather.way.utils.Constants.CELSIUS
import weather.way.utils.Constants.CLOUDS
import weather.way.utils.Constants.RAIN
import weather.way.utils.Constants.SNOW
import weather.way.utils.Constants.SUN
import weather.way.utils.convertFahrenheitToCelsius
import weather.way.utils.convertTimestampToTimeForecast

class WeatherPerHourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: WeatherPerHourCardBinding = WeatherPerHourCardBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(forecast: HourlyForecast) {
        with(binding) {
            tvHourLabel.text = convertTimestampToTimeForecast(forecast.dt)
            tvHourTemp.text = convertFahrenheitToCelsius(forecast.main.temp).toString() + CELSIUS
            setRightImage(forecast)
        }
    }

    private fun setRightImage(hourlyForecast: HourlyForecast) {
        if (hourlyForecast.weather[0].main == CLOUDS) {
            Glide.with(itemView).load(R.drawable.ic_cloud_vector)
                .into(binding.ivWeather)
        }
        if (hourlyForecast.weather[0].main == RAIN) {
            Glide.with(itemView).load(R.drawable.ic_rain_vector)
                .into(binding.ivWeather)
        }
        if (hourlyForecast.weather[0].main == SNOW) {
            Glide.with(itemView).load(R.drawable.ic_snow_vector)
                .into(binding.ivWeather)
        }
        if (hourlyForecast.weather[0].main == SUN) {
            Glide.with(itemView).load(R.drawable.ic_sun_vector)
                .into(binding.ivWeather)
        }
    }
}