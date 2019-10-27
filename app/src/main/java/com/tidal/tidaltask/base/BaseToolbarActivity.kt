package com.tidal.tidaltask.base

import android.view.View
import com.tidal.tidaltask.views.CustomToolbar

abstract class BaseToolbarActivity : BaseActivity(){

    lateinit var toolbar: CustomToolbar

    fun setUpToolbar(toolbar: CustomToolbar) {
        this.toolbar = toolbar
        toolbar.setBackButtonClickListener(View.OnClickListener { onBack() })
    }

    override fun getCustomToolbar(): CustomToolbar {
        return toolbar
    }

    fun setToolBarTitle(title: String) {
        toolbar.setTitle(title)
    }

    fun setRightText(text: String, color: Int) {
        toolbar.setRightText(text, color)
    }

    fun setBackButtonVisibility(visibility: Int) {
        when (visibility) {
            View.VISIBLE ->
                toolbar.showBackButton()
            else ->
                toolbar.hideBackButton()
        }
    }
}