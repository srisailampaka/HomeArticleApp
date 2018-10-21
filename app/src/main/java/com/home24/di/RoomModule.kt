package com.home24.di

import android.app.Application
import android.arch.persistence.room.Room
import com.home24.data.db.AppDatabase
import com.home24.data.db.ArticleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import javax.inject.Inject

@Module
class RoomModule() {

    // private var appDb: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "db_demo").build()
    // @Inject
    // internal lateinit var appDb: AppDatabase

   /* @Singleton
    @Provides
    fun providesAppDb(appDb: AppDatabase): AppDatabase = appDb
*/
    @Singleton
    @Provides
    fun providesArticleDao(appDb: AppDatabase): ArticleDao = appDb.articleDao()


}