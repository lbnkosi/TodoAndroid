package com.dstv.tododstv.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubTask(
    var title: String = "",
    var note: String = ""
):Parcelable
