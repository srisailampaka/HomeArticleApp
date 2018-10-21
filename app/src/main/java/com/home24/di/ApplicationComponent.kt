package com.home24.di

import android.app.Application
import com.home24.HomeApplication
import com.home24.data.db.AppDatabase
import com.home24.data.db.ArticleDao
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        RepositoryModule::class,
        ViewModelModule::class,
        RoomModule::class
))
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance // you'll call this when setting up Dagger
        fun appDatabase(appDatabase: AppDatabase): Builder

        fun build(): ApplicationComponent


    }

    fun inject(app: HomeApplication)

}

