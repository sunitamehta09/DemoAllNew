package com.earshot.apiservice

import android.app.Activity
import android.content.Context

interface CommonView {

    /**
     * Show progress when api is being called
     *
     * @param showProgress Flag to show hide progress view
     */
    fun showProgress(showProgress: Boolean)
}