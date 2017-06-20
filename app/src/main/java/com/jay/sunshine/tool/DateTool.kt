package com.jay.sunshine.tool

import com.jay.sunshine.db.ForecastProvider
import java.text.DateFormat
import java.util.*

/**
 * Created by jay on 17/6/1.
 */

fun convertDate(date: Long): String {
    val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
    return df.format(date * 1000)
}

fun todayTimeSpan() = System.currentTimeMillis() /
        ForecastProvider.DAY_IN_MILLS * ForecastProvider.DAY_IN_MILLS


fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this * 1000)
}