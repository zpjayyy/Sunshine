package com.jay.sunshine.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.jay.sunshine.R
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.tool.*
import com.jay.sunshine.ui.base.BaseActivity
import com.jay.sunshine.ui.base.ToolbarActivity
import org.jetbrains.anko.find

/**
 * Created by jay on 17/6/1.
 */
class DetailActivity : BaseActivity(), ToolbarManager {

    companion object {
        val DATA = "DetailActivity:data"
    }

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val weatherDescription: TextView by bindView(R.id.weather_description)
    val maxTemperature: TextView by bindView(R.id.max_temperature)
    val minTemperature: TextView by bindView(R.id.min_temperature)

//    override fun canBack(): Boolean {
//        return true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<ModelForecast>(DATA)

//        supportActionBar?.title = "suzhou"

        toolbarTitle = "suzhou"

        enableHomeAsUp {
            onBackPressed()
        }

        with(data) {
            supportActionBar?.subtitle = date.toDateString()
            weatherDescription.text = data.description
            bindTemperature(high to maxTemperature, low to minTemperature)

//            maxTemperature.text = high.toString()
//            minTemperature.text = low.toString()
        }

    }

    private fun bindTemperature(vararg views: Pair<Float, TextView>) = views.forEach {
        it.second.text = String.format(getString(R.string.format_temperature),
                getCelsiusTemp(it.first))

        val tempInt = (it.first - 273.15).toInt()

        it.second.setTextColor(resources.getColor(when (tempInt) {
            in -15..0 -> android.R.color.holo_orange_dark
            in 25..40-> android.R.color.holo_red_dark
            else -> android.R.color.holo_green_dark
        }))
    }
}