package com.home24.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.home24.data.db.AppDatabase
import com.home24.data.db.ArticleDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext

}