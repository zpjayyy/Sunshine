package com.jay.sunshine.ui.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * Created by jay on 17/5/27.
 */
open class BaseFragment : Fragment(), AnkoLogger {

    val compositeDisposable = CompositeDisposable()


    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    inline fun toast(message: String) = activity.toast(message)
}
