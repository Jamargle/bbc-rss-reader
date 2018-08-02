package com.jmlb0003.bbcnews.di

import com.jmlb0003.bbcnews.presentation.news.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
