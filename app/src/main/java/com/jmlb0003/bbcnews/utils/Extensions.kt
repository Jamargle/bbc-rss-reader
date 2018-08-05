package com.jmlb0003.bbcnews.utils

import android.content.res.Resources
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmlb0003.bbcnews.R
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutResourceId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResourceId, this, attachToRoot)
}

internal fun Resources.isForTablet(): Boolean {
    return try {
        this.getBoolean(R.bool.is_tablet)
    } catch (ex: Resources.NotFoundException) {
        false
    }
}

internal fun AppCompatActivity.viewExists(@IdRes viewId: Int) = findViewById<View>(viewId) != null

internal fun Date.getTimeAgo(): DatePeriod {
    val currentTime = Date().time
    return when {
        currentTime - this.time <= 60 * 1000 -> DatePeriod.JustNow
        currentTime - this.time <= 60 * 60 * 1000 -> DatePeriod.MinutesAgo(((currentTime - this.time) / (60 * 1000)).toInt())
        currentTime - this.time <= 24 * 60 * 60 * 1000 -> DatePeriod.HoursAgo(((currentTime - this.time) / (60 * 60 * 1000)).toInt())
        else -> DatePeriod.FullTimeDate(SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(this))
    }
}
