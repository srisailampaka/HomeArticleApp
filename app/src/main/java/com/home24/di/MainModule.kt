package com.home24.di

import com.home24.data.db.AppDatabase
import com.home24.ui.main.review.ReviewFragment
import com.home24.ui.main.selection.SelectionFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeReviewFragmentInjector(): ReviewFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSelectionFragmentInjector(): SelectionFragment


}