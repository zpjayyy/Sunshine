package com.jay.sunshine.api.tool

import com.jay.sunshine.api.type.ForecastList
import com.jay.sunshine.api.type.ForecastResult
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.api.type.base.Forecast
import java.text.DateFormat
import java.util.*

/**
 * Created by jay on 17/5/31.
 */
class ForecastDataMapper {

    public fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.id, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(forecast.dt,
                forecast.weather[0].description,
                forecast.main.temp_max.toFloat(),
                forecast.main.temp_min.toFloat(),
                forecast.weather[0].icon)
    }
}