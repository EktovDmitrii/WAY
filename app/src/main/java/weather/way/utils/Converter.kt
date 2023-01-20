package weather.way.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import java.sql.Timestamp
import java.util.*

fun convertTimestampToTime(timestamp: Int?): String {
    if (timestamp == null) return ""
    val stamp = Timestamp((timestamp * 1000L))
    val pattern = "DD:MM:HH:mm"
    val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(pattern, Locale.getDefault())
    } else {
        TODO("VERSION.SDK_INT < N")
    }
    sdf.timeZone = TimeZone.getTimeZone("GMT+03")
    return sdf.format(stamp)
}

fun convertFahrenheitToCelsius(degrees: Double): Int {
    return (degrees - 273.15).toInt()
}