package com.jmlb0003.bbcnews

import android.app.Activity
import android.app.Application
import com.jmlb0003.bbcnews.di.DaggerReaderAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ReaderApplication : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerReaderAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

}
