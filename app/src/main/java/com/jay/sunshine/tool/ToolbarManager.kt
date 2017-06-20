package com.jay.sunshine.tool

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.jay.sunshine.App
import com.jay.sunshine.R
import org.jetbrains.anko.toast

/**
 * Created by jay on 17/6/1.
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_settings -> App.instance.toast("Setting")
                else -> App.instance.toast("Unknow option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = (DrawerArrowDrawable(toolbar.context)).apply {
        progress = 1f
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

    fun View.slideExit() {
        if (translationY == 0f) animate().translationY(-height.toFloat())
    }

    fun View.slideEnter() {
        if (translationY < 0f) animate().translationY(0f)
    }
}