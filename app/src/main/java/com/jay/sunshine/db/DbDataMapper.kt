package com.jay.sunshine.db

import com.jay.sunshine.api.type.ForecastList
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.api.type.base.Forecast
import com.jay.sunshine.db.model.CityForecast
import com.jay.sunshine.db.model.DayForecast

/**
 * Created by jay on 17/5/31.
 */
class DbDataMapper() {


    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ModelForecast(date, description, high, low, icon)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: ModelForecast) = with(forecast) {
        DayForecast(date, description, high, low, icon, cityId)
    }

}