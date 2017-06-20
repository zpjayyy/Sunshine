package com.jay.sunshine.api

import com.jay.sunshine.api.type.ForecastResult
import com.jay.sunshine.api.type.HttpResult
import com.jay.sunshine.api.type.base.Forecast
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jay on 17/5/26.
 */
interface Sunshine {

    @GET("http://www.baidu.com")
    fun getWeather(): Flowable<String>

    @GET("data/2.5/forecast")
    fun getForecast(@Query("lat") lat: String, @Query("lon") lon: String): Flowable<ForecastResult>


}
