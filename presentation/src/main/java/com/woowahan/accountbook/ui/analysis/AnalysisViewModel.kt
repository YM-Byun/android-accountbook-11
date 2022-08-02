package com.woowahan.accountbook.ui.analysis

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.theme.spendingColors
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase,
) : ViewModel() {
    private val _totalSpend = MutableLiveData(0L)
    val totalSpend: LiveData<Long>
        get() = _totalSpend

    private val _ratioList = MutableLiveData<List<Pair<Long, Int>>>(emptyList())
    val ratioList: LiveData<List<Pair<Long, Int>>>
        get() = _ratioList

    private val _categoryList = MutableLiveData<List<Category>>(emptyList())
    val categoryList: LiveData<List<Category>>
        get() = _categoryList

    fun getRecords(date: String) {
        if (date.isNotEmpty()) {
            viewModelScope.launch {
                parseRecord(
                    getRecordsByMonthUseCase.execute(
                        date.year(),
                        date.month()
                    )
                )
            }
        }
    }

    fun parseRecord(records: List<Record>) {
        val spendingRecord = records.filter { it.type == DBHelper.SPENDING }
        _totalSpend.value = (spendingRecord.sumOf { it.price } * -1)
        val spendingRecordGroupByCategory = spendingRecord.groupBy { it.category }

        val properties = LongArray(spendingRecordGroupByCategory.size) { i -> 0 }
        val categories = ArrayList<Category>()
        val avg = IntArray(spendingRecordGroupByCategory.size) { i -> 0 }

        spendingRecordGroupByCategory.onEachIndexed { index, entry ->
            for (record in entry.value) {
                properties[index] += record.price
            }
            categories.add(entry.value.first().category)
        }

        val total = properties.sum()
        for (idx in properties.indices) {
            avg[idx] = ((properties[idx] / total) * 100).toInt()
        }

        val ratio = ArrayList<Pair<Long, Int>>()
        for (idx in properties.indices) {
            ratio.add(Pair(properties[idx], avg[idx]))
        }

        _ratioList.postValue(ratio)
        _categoryList.postValue(categories)
    }
}