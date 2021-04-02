package com.dstv.tododstv.core.extensions

import java.text.DateFormat
import java.util.*

fun getDate(): String {
    val date = Date()
    return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date)
}