package com.jmlb0003.bbcnews.di

import android.content.Context
import com.jmlb0003.bbcnews.ReaderApplication
import com.jmlb0003.bbcnews.presentation.navigation.NewsNavigator
import com.jmlb0003.bbcnews.utils.Schedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReaderAppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: ReaderApplication): Context = application

    @Provides
    @Singleton
    fun provideWorkSchedulers(): Schedulers = Schedulers()

    @Provides
    @Singleton
    fun provideNavigation(): NewsNavigator = NewsNavigator()

}
