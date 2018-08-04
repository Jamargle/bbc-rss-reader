package com.jmlb0003.bbcnews.di

import com.jmlb0003.bbcnews.utils.ServiceGenerator
import com.jmlb0003.bbcnews.data.BBCNewsApiClient
import com.jmlb0003.bbcnews.data.NetworkNewsRepositoryImp
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(): NetworkNewsRepository =
            NetworkNewsRepositoryImp(ServiceGenerator.createService(BBCNewsApiClient::class.java))

}