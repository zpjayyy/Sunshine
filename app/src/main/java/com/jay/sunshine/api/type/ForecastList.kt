package com.jay.sunshine.api.type

/**
 * Created by jay on 17/5/31.
 */
data class ForecastList(val id: Long,
                        val city: String,
                        val country: String,
                        val dailyForecast: List<ModelForecast>)