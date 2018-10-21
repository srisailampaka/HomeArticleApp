package com.home24.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.home24.HomeApplication
import com.home24.utils.CommonUtil
import com.home24.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * This Activity Class for implement common functionality
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // replace the fragment
    fun replaceTheFragment(viewId: Int, fragment: Fragment, backStackText: String) {
        supportFragmentManager.beginTransaction()
                .replace(viewId, fragment, backStackText)
                .addToBackStack(backStackText)
                .commit()
        supportFragmentManager.executePendingTransactions()
    }


    /*
    This methode helps to add font to app
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    override fun onBackPressed() {
        // show the Alert Dialog
        CommonUtil.showAlertDialog(this, "", getString(R.string.message), DialogInterface.OnClickListener { dialog, which ->
            finish()

        })


    }
}