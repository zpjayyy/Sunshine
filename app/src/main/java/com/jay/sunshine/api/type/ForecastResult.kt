package com.jay.sunshine.api.type

import com.jay.sunshine.api.type.base.City
import com.jay.sunshine.api.type.base.Forecast

/**
 * Created by jay on 17/5/31.
 */
data class ForecastResult(
        val cod: String,
        val message: String,
        val cnt: Int,
        val city: City,
        val list: List<Forecast>)