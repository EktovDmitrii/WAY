package weather.way.ui

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import weather.way.databinding.WeatherPerHourCardBinding
import weather.way.domain.model.HourlyForecast
import java.sql.Timestamp
import java.util.*

class WeatherPerHourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: WeatherPerHourCardBinding = WeatherPerHourCardBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(hourlyForecast: HourlyForecast) {
        binding.tvHourLabel.text = convertTimestampToTime(hourlyForecast.dt)
        binding.tvHourTemp.text = convertFahrenheitToCelsius(hourlyForecast.main.temp).toString() + "'C"
    }

    private fun convertTimestampToTime(timestamp: Int?):String{
        if (timestamp == null) return ""
        val stamp = Timestamp((timestamp * 1000L))
        val pattern = "HH:mm"
        val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat(pattern, Locale.getDefault())
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(stamp)
    }

    private fun convertFahrenheitToCelsius(degrees: Double): Int{
        return (degrees - 273.15).toInt()
    }

}