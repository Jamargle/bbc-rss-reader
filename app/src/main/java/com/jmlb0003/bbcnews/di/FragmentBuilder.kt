package com.jmlb0003.bbcnews.di

import com.jmlb0003.bbcnews.presentation.news.NewsFragment
import com.jmlb0003.bbcnews.presentation.newsdetail.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment

}
