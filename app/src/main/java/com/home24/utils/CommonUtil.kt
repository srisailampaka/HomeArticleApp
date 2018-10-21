package com.home24.utils

import android.content.Context
import android.app.AlertDialog
import android.content.DialogInterface
import android.net.ConnectivityManager
import com.home24.R

/**
 * This class to implement the common functionlitys for the app
 */
class CommonUtil {


    companion object {
        // method for check the network connection
        fun isNetworkStatusAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            connectivityManager?.let {
                it.activeNetworkInfo?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }

        // method for show the alert dialog
        fun showAlertDialog(context: Context, title: String, message: String, onClickListener: DialogInterface.OnClickListener) {
            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("YES", onClickListener)
            builder.setNeutralButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


    }


}