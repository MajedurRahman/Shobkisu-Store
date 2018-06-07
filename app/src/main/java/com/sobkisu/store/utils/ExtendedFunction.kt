package com.sobkisu.store.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.snackBar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
}

fun Any.log(message: String) {
    Log.e(javaClass.simpleName, " : $message")
}
