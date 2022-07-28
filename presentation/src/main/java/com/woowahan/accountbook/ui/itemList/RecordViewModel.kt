package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowahan.domain.model.RecordItem

class RecordViewModel : ViewModel() {
    private val _records = MutableLiveData<List<RecordItem>>()
    val records: LiveData<List<RecordItem>>
        get() = _records

    init {
        val list = ArrayList<RecordItem>()

        for (i in 0..10) {
            if (i % 2 == 0) {
                val dummy = RecordItem("type", "title", "payment", "-10,000")
                list.add(dummy)
            } else {
                val dummy = RecordItem("type", "title", "payment", "10,000")
                list.add(dummy)
            }
        }
        _records.value = list
    }
}