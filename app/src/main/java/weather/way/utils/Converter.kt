package weather.way.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import weather.way.domain.model.CommonInfo
import java.sql.Timestamp
import java.util.*

fun convertTimestampToTime(timestamp: Int?, commonInfo: CommonInfo): String {
    if (timestamp == null) return ""
    val stamp = Timestamp((timestamp * 1000L))
    val pattern = "HH:mm"
    val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(pattern, Locale.getDefault())
    } else {
        TODO("VERSION.SDK_INT < N")
    }
    sdf.timeZone = TimeZone.getTimeZone("GMT+${findRightTimezone(commonInfo)}")
    return sdf.format(stamp)
}

fun convertTimestampToTimeForecast(timestamp: Int?): String {
    if (timestamp == null) return ""
    val stamp = Timestamp((timestamp * 1000L))
    val pattern = "HH:mm"
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

fun findRightTimezone(commonInfo: CommonInfo): String{
    var timezone = ""
    if (commonInfo.city.timezone == 0){
        timezone = "00"
    }
    if (commonInfo.city.timezone == 3600){
        timezone = "01"
    }
    if (commonInfo.city.timezone == 7200){
        timezone = "02"
    }
    if (commonInfo.city.timezone == 10800){
        timezone = "03"
    }
    if (commonInfo.city.timezone == 12600){
        timezone = "03:30"
    }
    if (commonInfo.city.timezone == 14400){
        timezone = "04"
    }
    if (commonInfo.city.timezone == 16200){
        timezone = "04:30"
    }
    if (commonInfo.city.timezone == 18000){
        timezone = "05"
    }
    if (commonInfo.city.timezone == 19800){
        timezone = "05:30"
    }
    if (commonInfo.city.timezone == 20700){
        timezone = "05:45"
    }
    if (commonInfo.city.timezone == 21600){
        timezone = "06"
    }
    if (commonInfo.city.timezone == 23400){
        timezone = "06:30"
    }
    if (commonInfo.city.timezone == 25200){
        timezone = "07"
    }
    if (commonInfo.city.timezone == 28800){
        timezone = "08"
    }
    if (commonInfo.city.timezone == 32400){
        timezone = "09"
    }
    if (commonInfo.city.timezone == 34200){
        timezone = "09:30"
    }
    if (commonInfo.city.timezone == 36000){
        timezone = "10"
    }
    if (commonInfo.city.timezone == 37800){
        timezone = "10:30"
    }
    if (commonInfo.city.timezone == 39600){
        timezone = "11"
    }
    if (commonInfo.city.timezone == 41400){
        timezone = "11:30"
    }
    if (commonInfo.city.timezone == 43200){
        timezone = "12"
    }
    if (commonInfo.city.timezone == 45900){
        timezone = "12:45"
    }
    if (commonInfo.city.timezone == 46800){
        timezone = "13"
    }
    if (commonInfo.city.timezone == 50400){
        timezone = "14"
    }
    if (commonInfo.city.timezone == -3600){
        timezone = "-01"
    }
    if (commonInfo.city.timezone == -7200){
        timezone = "-02"
    }
    if (commonInfo.city.timezone == -10800){
        timezone = "-03"
    }
    if (commonInfo.city.timezone == -12600){
        timezone = "-03:30"
    }
    if (commonInfo.city.timezone == -14400){
        timezone = "-04"
    }
    if (commonInfo.city.timezone == -16200){
        timezone = "-04:30"
    }
    if (commonInfo.city.timezone == -18000){
        timezone = "-05"
    }
    if (commonInfo.city.timezone == -21600){
        timezone = "-06"
    }
    if (commonInfo.city.timezone == -25200){
        timezone = "-07"
    }
    if (commonInfo.city.timezone == -28800){
        timezone = "-08"
    }
    if (commonInfo.city.timezone == -32400){
        timezone = "-09"
    }
    if (commonInfo.city.timezone == -34200){
        timezone = "-09:30"
    }
    if (commonInfo.city.timezone == -3600){
        timezone = "-10"
    }
    if (commonInfo.city.timezone == -39600){
        timezone = "-11"
    }
    if (commonInfo.city.timezone == -43200){
        timezone = "-12"
    }
    return timezone
}