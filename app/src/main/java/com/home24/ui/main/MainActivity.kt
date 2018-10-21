package com.home24.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.home24.R
import com.home24.ui.BaseActivity
import com.home24.ui.main.selection.SelectionFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * This Activity Class for Main Screen
 */
class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceTheFragment(R.id.container, SelectionFragment.newInstance(), SelectionFragment::class.java.name)
    }

}
