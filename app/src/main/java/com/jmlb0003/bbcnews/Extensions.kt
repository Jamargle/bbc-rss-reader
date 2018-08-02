package com.jmlb0003.bbcnews

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutResourceId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResourceId, this, attachToRoot)
}