package com.dstv.tododstv.features.task

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dstv.tododstv.BR

class TaskBaseObservable(private var _title: String = "", private var _note: String = "") : BaseObservable() {

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }

    var note: String
        @Bindable get() = _note
        set(value) {
            _note = value
            notifyPropertyChanged(BR.note)
        }

    fun isRequestValid(): Boolean {
        if (title.isNotEmpty() && note.isNotEmpty()) {
            return true
        }
        return false
    }
}