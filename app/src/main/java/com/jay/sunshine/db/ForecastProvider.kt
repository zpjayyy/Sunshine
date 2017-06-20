package com.jay.sunshine.db

import com.jay.sunshine.api.type.ForecastList
import com.jay.sunshine.tool.todayTimeSpan

/**
 * Created by jay on 17/6/1.
 */
class ForecastProvider(val sources: List<ForecastDataSource> =
                       ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb())
    }

    fun requestByZipCode(zipCode: Long, date: Long): ForecastList?
            = sources.first().requestForecastByZipCode(zipCode, date)


    private fun requestSource(source: ForecastDataSource, zipCode: Long, date: Long):
            ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && !res.dailyForecast.isEmpty()) res else null
    }



}