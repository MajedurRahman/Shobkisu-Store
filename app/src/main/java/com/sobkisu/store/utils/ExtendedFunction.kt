package com.sobkisu.store.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast


fun Context.toast(message: String) {
    try {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.snackBar(message: String) {
    try {
        val activity = this as Activity
        Snackbar.make(activity.window.decorView, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.positiveSnackBar(message: String, view: View? = null) {
    val activity = this as Activity

    val snackbar: Snackbar = if (view == null) {
        Snackbar.make(activity.window.decorView, message, Snackbar.LENGTH_LONG)
    } else {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    }
    val sandbarView = snackbar.view
    sandbarView.setBackgroundColor(Color.parseColor("#f64747"))
    val textView = sandbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    textView.setTypeface(null, Typeface.BOLD)
    snackbar.show()
}

fun Context.negativeSnackBar(message: String, view: View? = null) {
    val activity = this as Activity
    val snackbar: Snackbar
    snackbar = if (view == null) {
        Snackbar.make(activity.window.decorView, message, Snackbar.LENGTH_LONG)
    } else {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    }
    val sandbarView = snackbar.view
    sandbarView.setBackgroundColor(Color.parseColor("#ffea00"))
    val textView = sandbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.BLACK)
    snackbar.show()
}

fun Any.log(message: String) {
    try {
        Log.e(javaClass.simpleName, " : $message")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
