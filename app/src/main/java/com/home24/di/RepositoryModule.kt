package com.home24.di

import com.home24.data.HomeServices
import com.home24.data.repository.HomeRepository
import com.home24.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
 class RepositoryModule {

    @Provides
    fun provideHomeRepo(homeServices: HomeServices): HomeRepository = HomeRepositoryImpl(homeServices)

}