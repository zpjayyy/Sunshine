package com.jay.sunshine.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jay.sunshine.R
import com.jay.sunshine.api.Retrofits
import com.jay.sunshine.tool.bindView
import com.jay.sunshine.ui.base.ToolbarActivity
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : ToolbarActivity() {

    val fab: FloatingActionButton by bindView(R.id.fab)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            android.support.design.widget.Snackbar.make(view, "Replace with your own action", android.support.design.widget.Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


//        rxJavaTest()

        flowTest()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            startActivity<SettingActivity>(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun rxJavaTest() {
        Observable.create<Int> {
            for (index in 0..Int.MAX_VALUE) {
                it.onNext(index)
            }
        }.subscribeOn(Schedulers.io())
                .map { it.toString() }
//                .flatMap {
//                    val list = ArrayList<String>()
//                    for (index in 0..3) {
//                        list.add(it)
//                    }
//                    Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS)
//                }
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {

                    var disposable: Disposable? = null

                    override fun onNext(t: String?) {
                        info { t }
                    }

                    override fun onError(e: Throwable?) {
                        info { "error" }
                    }

                    override fun onComplete() {
                        info { "complete" }
                    }

                    override fun onSubscribe(d: Disposable?) {
                        info { "subscribe" }
                        disposable = d
                    }

                })
    }

    private fun flowTest() {

        val upStream = Flowable.create<Int>(FlowableOnSubscribe {

            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onComplete()

        }, BackpressureStrategy.ERROR)


        val downStream = object: Subscriber<Int> {
            override fun onComplete() {
                info { "onComplete" }
            }

            override fun onNext(t: Int?) {
                info { t.toString() }
            }

            override fun onError(t: Throwable?) {
                info { t.toString() }
            }

            override fun onSubscribe(s: Subscription) {
                info { "onSubscribe" }
                s.request(Long.MAX_VALUE)
            }
        }

        upStream
                .subscribe(downStream)
    }
}
