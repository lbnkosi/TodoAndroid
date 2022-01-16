package com.lbnkosi.todoapp.features.task

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.lbnkosi.todoapp.BR

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

    //We don't check if the note is empty because sometimes there's no need for a note
    fun isRequestValid(): Boolean {
        if (title.isNotEmpty()) {
            return true
        }
        return false
    }
}