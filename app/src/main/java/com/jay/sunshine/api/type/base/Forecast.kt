package com.jay.sunshine.api.type.base

/**
 * Created by jay on 17/5/27.
 */
data class Forecast(
        val dt: Long,
        val main: Main,
        val weather: List<Weather>,
        val clounds: Clounds,
        val rain: Rain,
        val sys: Sys,
        val dt_txt: String
) {

    data class Main(
            val temp: String,
            val temp_min: String,
            val temp_max: String,
            val pressure: String,
            val sea_level: String,
            val grnd_level: String,
            val humidity: String,
            val temp_kf: String
    )

    data class Weather(
            val id: Long,
            val main: String,
            val description: String,
            val icon: String
    )

    data class Clounds(val all: String)


    data class Wind(val speed: String, val deg: String)

    class Rain()

    data class Sys(val pod: String)

}