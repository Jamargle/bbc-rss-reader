package com.jmlb0003.bbcnews.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class Schedulers @Inject constructor() {

    fun getBackgroundThread(): Scheduler = io.reactivex.schedulers.Schedulers.io()

    fun getUiThread(): Scheduler = AndroidSchedulers.mainThread()

}