package com.home24

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Room
import com.home24.data.db.AppDatabase
import com.home24.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

/**
 * This is the application class
 */
class HomeApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        var appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "article-db").allowMainThreadQueries().build()

        DaggerApplicationComponent.builder().application(this).appDatabase(appDatabase)
                .build().inject(this)


        // setting custom font for the app
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("font/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}