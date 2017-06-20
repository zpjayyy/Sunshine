package com.jay.sunshine.api.type

import com.google.gson.annotations.SerializedName
import com.jay.sunshine.api.type.base.City

/**
 * Created by jay on 17/5/27.
 */
data class HttpResult<T>(
        val cod: String,
        val message: String,
        val cnt: Int,
        val list: T) {

}