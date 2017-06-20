package com.jay.sunshine.db

import android.database.sqlite.SQLiteDatabase
import com.jay.sunshine.api.type.ForecastList
import com.jay.sunshine.api.type.ModelForecast
import com.jay.sunshine.db.model.CityForecast
import com.jay.sunshine.db.model.DayForecast
import com.jay.sunshine.db.table.CityForecastTable
import com.jay.sunshine.db.table.DayForecastTable
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by jay on 17/5/31.
 */
class ForecastDb(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DbDataMapper = DbDataMapper())
    : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        city?.let { dataMapper.convertToDomain(city) }
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())

            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }

    }


    fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any?>) -> T): List<T> =
            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any?>) -> T): T? =
            parseOpt(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
            Array<out Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }
}