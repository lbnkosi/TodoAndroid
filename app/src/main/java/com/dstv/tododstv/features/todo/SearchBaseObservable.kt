package com.dstv.tododstv.features.todo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dstv.tododstv.BR

class SearchBaseObservable(private var _keyword: String = "") : BaseObservable() {

    var keyword: String
        @Bindable get() = _keyword
        set(value) {
            _keyword = value
            notifyPropertyChanged(BR.keyword)
        }

}