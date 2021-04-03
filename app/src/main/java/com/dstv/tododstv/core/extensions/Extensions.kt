package com.dstv.tododstv.core.extensions

import com.dstv.tododstv.core.models.Category
import java.text.DateFormat
import java.util.*

fun getDate(): String {
    val date = Date()
    return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date)
}

fun ArrayList<Category>.sortedCategory(): List<Category> {
    return this.sortedWith(compareBy { it.count }).reversed()
}