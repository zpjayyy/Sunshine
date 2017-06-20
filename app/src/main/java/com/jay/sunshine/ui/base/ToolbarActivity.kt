package com.jay.sunshine.ui.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.jay.sunshine.R
import com.jay.sunshine.tool.bindView

/**
 * Created by jay on 17/5/26.
 */
open class ToolbarActivity : BaseActivity() {

    val toolbar: Toolbar by bindView(R.id.toolbar)

    open fun canBack(): Boolean {
        return false
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val actionBar = supportActionBar

        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true)
            if (canBack()) {
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

}