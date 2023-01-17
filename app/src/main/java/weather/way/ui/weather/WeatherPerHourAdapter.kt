package weather.way.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import weather.way.R
import weather.way.domain.model.HourlyForecast

class WeatherPerHourAdapter :
    ListAdapter<HourlyForecast, WeatherPerHourViewHolder>(WeatherPerHourDiffCallBack) {

    var myData : List<HourlyForecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherPerHourViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_per_hour_card, parent, false)
        return WeatherPerHourViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherPerHourViewHolder, position: Int) {
        val weather = myData[position]
        holder.bind(weather)
    }
}