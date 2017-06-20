package com.jay.sunshine.ui

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jay.sunshine.R
import com.jay.sunshine.tool.DelegatesExt
import com.jay.sunshine.tool.ToolbarManager
import com.jay.sunshine.tool.bindView
import com.jay.sunshine.ui.base.BaseActivity
import org.jetbrains.anko.find

/**
 * Created by jay on 17/6/2.
 */
class SettingActivity : BaseActivity(), ToolbarManager {

    companion object {
        val ZIP_CODE = "zip_code"
        val DEFAULT_ZIP = 1886760L
    }

    var zipCode: Long by DelegatesExt.Preference(this, ZIP_CODE, DEFAULT_ZIP)

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val cityCode: TextInputEditText by bindView(R.id.cityCode)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        enableHomeAsUp { onBackPressed() }

        cityCode.setText(zipCode.toString())
    }

    override fun onBackPressed() {
        zipCode = cityCode.text.toString().toLong()
        super.onBackPressed()
    }
}
