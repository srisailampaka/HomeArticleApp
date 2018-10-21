package com.home24.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.home24.ui.main.review.ReviewViewModel
import com.home24.ui.main.selection.SelectionViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SelectionViewModel::class)
    abstract fun bindSelectionViewModel(viewModel: SelectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel::class)
    abstract fun bindReviewViewModel(viewModel: ReviewViewModel): ViewModel


}