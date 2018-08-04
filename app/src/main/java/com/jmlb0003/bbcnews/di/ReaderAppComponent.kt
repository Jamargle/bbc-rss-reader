package com.jmlb0003.bbcnews.di

import android.app.Application
import com.jmlb0003.bbcnews.ReaderApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ReaderAppModule::class,
    ViewModelModule::class,
    ActivityBuilder::class,
    RepositoryModule::class])
interface ReaderAppComponent : AndroidInjector<ReaderApplication> {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: ReaderApplication): Builder

        fun build(): ReaderAppComponent

    }

}
