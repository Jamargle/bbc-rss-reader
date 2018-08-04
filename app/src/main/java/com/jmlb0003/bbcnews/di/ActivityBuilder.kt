package com.jmlb0003.bbcnews.di

import com.jmlb0003.bbcnews.presentation.news.MainActivity
import com.jmlb0003.bbcnews.presentation.newsdetail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun bindDetailActivity(): DetailActivity

}
