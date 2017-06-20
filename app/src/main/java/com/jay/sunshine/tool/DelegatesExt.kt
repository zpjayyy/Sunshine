package com.jay.sunshine.tool

import android.content.Context
import java.util.function.LongPredicate
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by jay on 17/6/2.
 */
object DelegatesExt {


    fun <T : Any> preference(context: Context, name: String, default: T) =
            Preference(context, name, default)


    class Preference<T>(val context: Context, val name: String, val default: T)
        : ReadWriteProperty<Any?, T> {

        val prefs by lazy {
            context.getSharedPreferences("default", Context.MODE_PRIVATE)
        }

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return findPreference(name, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        }

        private fun <T> findPreference(name: String, default: T): T = with(prefs) {
            val res: Any = when (default) {
                is Long -> getLong(name, default)
                is String -> getString(name, default)
                is Int -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float -> getFloat(name, default)
                else -> throw IllegalArgumentException(
                        "This type can not be saved into Preferences"
                )
            }
            res as T
        }

        private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
            when (value) {
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float -> putFloat(name, value)
                else -> throw IllegalArgumentException(
                        "This type can not be save into preference"
                )
            }.apply()
        }
    }
}



