package com.jay.sunshine.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jay.sunshine.R
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.api.type.base.Forecast
import com.jay.sunshine.tool.bindView
import com.jay.sunshine.tool.convertDate
import com.jay.sunshine.tool.getCelsiusTemp

/**
 * Created by jay on 17/5/26.
 */
class ForecastAdapter(val items: List<ModelForecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>() {

    val VIEW_TYPE_TODAY = 0
    val VIEW_TYPE_FUTURE_DAY = 1

    var clickHandler: ((data: ModelForecast) -> Unit)? = null

    override fun onBindViewHolder(viewHolder: ForecastAdapterViewHolder, position: Int) {
        val context = viewHolder.itemView.context

        with(items[position]) {
            viewHolder.iconView.setBackgroundResource(R.drawable.art_clear)
            viewHolder.descriptionView.text = description

            val highTemp = String.format(context.getString(R.string.format_temperature),
                    getCelsiusTemp(high))
            viewHolder.highTempView.text = highTemp

            val lowTemp = String.format(context.getString(R.string.format_temperature),
                    getCelsiusTemp(low))
            viewHolder.lowTempView.text = lowTemp

            viewHolder.dateView.text = convertDate(date)


            viewHolder.itemView.setOnClickListener(View.OnClickListener {
                clickHandler?.invoke(this)
            })
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapterViewHolder {

        var layoutId: Int = 0

        when (viewType) {
            VIEW_TYPE_TODAY ->
                layoutId = R.layout.list_item_forecast_today

            VIEW_TYPE_FUTURE_DAY ->
                layoutId = R.layout.list_item_forecast

            else ->
                throw IllegalArgumentException("Invalid view type, value of " + viewType)
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ForecastAdapterViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_TODAY else VIEW_TYPE_FUTURE_DAY
    }

    fun setOnItemClick(listener: ((data: ModelForecast) -> Unit)) {
        clickHandler = listener
    }

    class ForecastAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val iconView: ImageView by bindView(R.id.weather_icon)
        val dateView: TextView by bindView(R.id.date)
        val descriptionView: TextView by bindView(R.id.weather_description)
        val highTempView: TextView by bindView(R.id.high_temperature)
        val lowTempView: TextView by bindView(R.id.low_temperature)

    }

}