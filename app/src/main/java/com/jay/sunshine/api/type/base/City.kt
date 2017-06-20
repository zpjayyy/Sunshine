package com.jay.sunshine.api.type.base

/**
 * Created by jay on 17/5/27.
 */
data class City(
        val id: Long,
        val name: String,
        val coord: Coord,
        val country: String
) {


    data class Coord(val lat: String, val lon: String)
}