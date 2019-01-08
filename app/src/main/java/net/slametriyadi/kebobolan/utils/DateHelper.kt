package net.slametriyadi.kebobolan.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {
        fun parseDate(inputDateString: String, inputDateFormat: SimpleDateFormat, outputDateFormat: SimpleDateFormat): String? {
            var outputDateString: String? = null
            try {
                val date: Date = inputDateFormat.parse(inputDateString)
                outputDateString = outputDateFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return outputDateString
        }
        fun inputDateFormat() = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        fun outputDateFormat() = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)
        var inputTimeFormat = SimpleDateFormat("HH:mm:ssZ", Locale.US)
        var outputTimeFormat = SimpleDateFormat("HH:mm", Locale.US)
    }
}
