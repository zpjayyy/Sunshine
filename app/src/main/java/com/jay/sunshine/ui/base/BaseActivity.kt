package com.jay.sunshine.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.jay.sunshine.BuildConfig
import com.jay.sunshine.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.reactivestreams.Subscription

/**
 * Created by jay on 17/5/26.
 */
open class BaseActivity : AppCompatActivity(), AnkoLogger {

    lateinit var dialog: ProgressDialog

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = ProgressDialog(this)
        dialog.isIndeterminate = true
        dialog.setMessage(getString(R.string.please_wait_moment))
        dialog.setCanceledOnTouchOutside(false)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun showProcess(show: Boolean) {
        if (show) dialog.show() else dialog.dismiss()
    }

    fun logd(message: String, tag: String = this::class.java.simpleName) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

}