package weather.way.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import weather.way.databinding.WeatherPerHourCardBinding
import weather.way.domain.model.HourlyForecast

class WeatherPerHourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: WeatherPerHourCardBinding = WeatherPerHourCardBinding.bind(itemView)

    fun bind(hourlyForecast: HourlyForecast) {
        binding.tvHourLabel.text = hourlyForecast.dt_txt
        binding.tvHourTemp.text = hourlyForecast.main.temp.toString()
    }
}