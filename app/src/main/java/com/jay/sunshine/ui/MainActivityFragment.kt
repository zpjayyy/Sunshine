package com.jay.sunshine.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jay.sunshine.R
import com.jay.sunshine.api.Retrofits
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.api.tool.ForecastDataMapper
import com.jay.sunshine.db.ForecastDb
import com.jay.sunshine.db.ForecastProvider
import com.jay.sunshine.tool.DelegatesExt
import com.jay.sunshine.tool.bindView
import com.jay.sunshine.tool.todayTimeSpan
import com.jay.sunshine.ui.adapter.ForecastAdapter
import com.jay.sunshine.ui.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : BaseFragment() {

    val recyclerWeather: RecyclerView by bindView(R.id.recycler_weather)

    lateinit var adapter: ForecastAdapter

    val items = ArrayList<ModelForecast>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
//        getForcecast()
        getForecastFromDb()
    }

    fun setUpRecyclerView() {
        recyclerWeather.layoutManager = LinearLayoutManager(context)

        adapter = ForecastAdapter(items)
        recyclerWeather.adapter = adapter

        adapter.setOnItemClick { data ->
            activity.startActivity<DetailActivity>(
                    DetailActivity.DATA to data
            )
        }

    }

    fun getForecastFromDb() {
        val zipCode: Long by DelegatesExt.preference(context, SettingActivity.ZIP_CODE,
                SettingActivity.DEFAULT_ZIP)
        doAsync {
            val list = ForecastProvider().requestByZipCode(zipCode, todayTimeSpan())

            info(list.toString())

            uiThread {
                if (list != null) {
                    items.addAll(list.dailyForecast)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun getForcecast() {
        info("id = " + Thread.currentThread().id)
        addDisposable(Retrofits.SINGLETON
                .getForecast("31.307436", "120.661738")
                .map { ForecastDataMapper().convertFromDataModel(it) }
                .doOnNext {
                    info("id = " + Thread.currentThread().id)
                    ForecastDb().saveForecast(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    info("id = " + Thread.currentThread().id)
                    items.addAll(it.dailyForecast)
                    recyclerWeather.adapter.notifyDataSetChanged()
                }))
    }
}
