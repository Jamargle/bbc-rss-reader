package com.jmlb0003.bbcnews.di

import android.content.Context
import com.jmlb0003.bbcnews.ReaderApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReaderAppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: ReaderApplication): Context = application

}
