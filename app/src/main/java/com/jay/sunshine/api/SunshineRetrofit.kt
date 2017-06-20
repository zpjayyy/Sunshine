package com.jay.sunshine.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jay on 17/5/26.
 */
class SunshineRetrofit {

    companion object {

        val ENDPOINT = "http://api.openweathermap.org/"

        val APPID = "8d5590f5210bc407fd3a12e4cb6980b5"

        var DEBUG = true
    }

    var service: Sunshine


    val GSON: Gson = GsonBuilder()
            .create()

    init {

        val clientBuild = OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)

        if (DEBUG) {
            val logging = HttpLoggingInterceptor();
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuild.addInterceptor(logging)
        }

        val appIdInterceptor = Interceptor {
            chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("APPID", APPID)
                    .build()
            val request = original.newBuilder()
                    .url(url)
                    .build()
            chain.proceed(request)
        }

        clientBuild.addInterceptor(appIdInterceptor)

        val client = clientBuild.build()

        val build = Retrofit.Builder()
                .baseUrl(HttpUrl.parse(ENDPOINT))
                .addConverterFactory(GsonConverterFactory.create(GSON))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)

        val retrofit = build.build()

        service = retrofit.create(Sunshine::class.java)
    }
}
