package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.accountUseCase.DeleteRecordsUseCase
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase,
    private val deleteRecordsUseCase: DeleteRecordsUseCase
) : ViewModel() {
    private val _records = MutableLiveData<List<Record>>(emptyList())
    val records: LiveData<List<Record>>
        get() = _records

    val leftBtnOnClick = MutableLiveData(true)
    val rightBtnOnClick = MutableLiveData(false)

    private var _totalIncome = 0L
    val totalIncome: Long
        get() = _totalIncome

    private var _totalSpending = 0L
    val totalSpending: Long
        get() = _totalSpending

    fun getRecords(date: String) {
        if (date.isNotEmpty()) {
            viewModelScope.launch {
                val recordList = getRecordsByMonthUseCase.execute(
                    date.year(),
                    date.month()
                )

                _totalIncome = recordList.filter { it.type == DBHelper.INCOME }.sumOf { it.price }
                _totalSpending =
                    recordList.filter { it.type == DBHelper.SPENDING }.sumOf { -it.price }
                _records.postValue(recordList)
            }
        }
    }

    suspend fun deleteItems(records: List<Record>) {
        deleteRecordsUseCase.execute(records)
    }
}