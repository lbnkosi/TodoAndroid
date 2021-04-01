package com.dstv.tododstv.core.extensions

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.FragmentManager

fun Context.getFragManager(): FragmentManager? {
    val cont = (this as ContextThemeWrapper).baseContext
    return if (cont is AppCompatActivity) {
        cont.supportFragmentManager
    } else {
        null
    }
}