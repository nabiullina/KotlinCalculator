package com.sagegirl.logger

import android.util.Log

object Logger {
    fun info(message: String) {
        Log.i("info", message)
    }

    fun error(message: String) {
        Log.e("error", message)
    }

    fun warning(message: String) {
        Log.w("warning", message)
    }
}