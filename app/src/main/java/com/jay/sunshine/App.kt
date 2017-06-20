package com.jay.sunshine

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.Log
import com.facebook.stetho.Stetho
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins
import org.jetbrains.anko.toast
import kotlin.properties.Delegates

/**
 * Created by jay on 17/5/26.
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()

        if (isMainProcess(this)) {
            instance = this

            Stetho.initializeWithDefaults(this)

            RxJavaPlugins.setErrorHandler(Consumer { toast(it.toString()) })
        }
    }


    fun isMainProcess(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfoList = am.runningAppProcesses
        val mainProcessName = context.packageName
        val myPid = android.os.Process.myPid()

        return processInfoList.any {
            it.pid == myPid && it.processName == mainProcessName
        }
    }

}
