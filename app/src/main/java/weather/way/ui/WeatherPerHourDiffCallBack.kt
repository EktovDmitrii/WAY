package weather.way.ui

import androidx.recyclerview.widget.DiffUtil
import weather.way.domain.model.HourlyForecast

object WeatherPerHourDiffCallBack: DiffUtil.ItemCallback<HourlyForecast>() {
    override fun areItemsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
        return oldItem.main.temp == newItem.main.temp
    }

    override fun areContentsTheSame(oldItem: HourlyForecast, newItem: HourlyForecast): Boolean {
        return oldItem == newItem
    }
}