package com.woowahan.accountbook.ui.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Record

class AnalysisViewModel : ViewModel() {
    private val _ratioList = MutableLiveData<List<Pair<Long, Float>>>(emptyList())
    val ratioList: LiveData<List<Pair<Long, Float>>>
        get() = _ratioList

    private val _categoryList = MutableLiveData<List<Category>>(emptyList())
    val categoryList: LiveData<List<Category>>
        get() = _categoryList


    fun getAnalysisResult(records: List<Record>) {
        val spendingRecord = records.filter { it.type == DBHelper.SPENDING }
        val spendingRecordGroupByCategory = spendingRecord.groupBy { it.category }

        val properties = LongArray(spendingRecordGroupByCategory.size) { i -> 0 }
        val categories = ArrayList<Category>()
        val avg = FloatArray(spendingRecordGroupByCategory.size) { i -> 0f }

        spendingRecordGroupByCategory.onEachIndexed { index, entry ->
            for (record in entry.value) {
                properties[index] += record.price
            }
            categories.add(entry.value.first().category)
        }

        val total = properties.sum()
        for (idx in properties.indices) {
            avg[idx] = properties[idx] / total.toFloat()
        }

        val ratio = ArrayList<Pair<Long, Float>>()
        for (idx in properties.indices) {
            ratio.add(Pair(properties[idx], avg[idx]))
        }

        _ratioList.postValue(ratio)
        _categoryList.postValue(categories)
    }
}