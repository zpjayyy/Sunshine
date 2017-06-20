package com.jay.sunshine.db.model


/**
 * Created by jay on 17/5/31.
 */
class DayForecast(var map: MutableMap<String, Any?>) {

    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Float by map
    var low: Float by map
    var icon: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Float, low: Float,
                icon: String, cityId: Long) : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.icon = icon
        this.cityId = cityId
    }

}