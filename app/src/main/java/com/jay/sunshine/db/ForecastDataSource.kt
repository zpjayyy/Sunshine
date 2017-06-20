package com.jay.sunshine.db

import com.jay.sunshine.api.type.ForecastList

/**
 * Created by jay on 17/6/1.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}