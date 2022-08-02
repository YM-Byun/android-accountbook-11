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
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase,
) : ViewModel() {
    private val _records = MutableLiveData<List<Record>>(emptyList())
    val records: LiveData<List<Record>>
        get() = _records

    fun getRecords(date: String) {
        if (date.isNotEmpty()) {
            viewModelScope.launch {
                _records.postValue(
                    getRecordsByMonthUseCase.execute(
                        date.year(),
                        date.month()
                    )
                )
                parseRecord()
            }
        }
    }

    fun parseRecord(): Pair<List<Float>, ArrayList<Color>> {
        val spendingRecord = _records.value!!.filter { it.type == DBHelper.SPENDING }
        val spendingGroup = spendingRecord.groupBy { it.category }
        val properties = LongArray(spendingGroup.size) { i -> 0 }
        val colors = ArrayList<Color>()
        val avg = FloatArray(spendingGroup.size) { i -> 0f }

        spendingGroup.onEachIndexed { index, entry ->
            for (record in entry.value) {
                properties[index] += record.price
            }
            colors.add(spendingColors[entry.value.first().category.color])
        }

        val total = properties.sum().toFloat()
        for (idx in properties.indices) {
            avg[idx] = (properties[idx] / total) * 100
        }

        return Pair(avg.toList(), colors)
    }
}