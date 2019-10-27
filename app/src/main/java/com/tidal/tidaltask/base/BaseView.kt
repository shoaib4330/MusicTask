package com.tidal.tidaltask.base

interface BaseView {
    fun showError(message: String)

    fun onOffProgressBar(toShow:Boolean)
}