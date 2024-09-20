package com.mss.weatherapp.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtil {
    companion object {
        fun getFormattedDateMonth(date: String): String {
            val dateParser = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val formatter = SimpleDateFormat("MMM d", Locale.ENGLISH)
            return try {
                formatter.format(dateParser.parse(date) ?: "")
            } catch (e: Exception) {
                ""
            }
        }

        fun getFormattedDateMonthShort(date: String): String {
            val dateParser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            val formatter = SimpleDateFormat("MMM d", Locale.ENGLISH)

            return formatter.format(dateParser.parse(date) ?: "")
        }

        fun getFormattedTime(time: String): String {
            try {
                var updatedtime = time.split(" ")[1]
                val sdf = SimpleDateFormat("HH:mm")
                val dateObj = sdf.parse(updatedtime)
                return SimpleDateFormat("hh:mm aa").format(dateObj)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return time
        }

        fun getDateFromEpoch(timeInMillis: Long): String {
            val dateFormatter = SimpleDateFormat("MMM d", Locale.ENGLISH)
            return dateFormatter.format(Date(timeInMillis))
        }

    }

}