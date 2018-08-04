package com.jmlb0003.bbcnews.di

import com.jmlb0003.bbcnews.presentation.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

}
