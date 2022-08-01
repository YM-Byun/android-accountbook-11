package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowahan.domain.model.Record

class RecordViewModel : ViewModel() {
    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records

    val leftBtnOnClick = MutableLiveData(true)
    val rightBtnOnClick = MutableLiveData(true)
}