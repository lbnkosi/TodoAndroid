package com.dstv.tododstv.features.common

interface TaskCallback {
    fun onComplete(refresh : Boolean = true)
}