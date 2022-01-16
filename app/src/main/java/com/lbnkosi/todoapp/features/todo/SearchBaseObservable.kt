package com.lbnkosi.todoapp.features.todo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.lbnkosi.todoapp.BR

class SearchBaseObservable(private var _keyword: String = "") : BaseObservable() {

    var keyword: String
        @Bindable get() = _keyword
        set(value) {
            _keyword = value
            notifyPropertyChanged(BR.keyword)
        }

}