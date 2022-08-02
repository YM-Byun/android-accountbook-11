package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
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
    val rightBtnOnClick = MutableLiveData(true)

    fun getRecords(date: String) {
        if (date.isNotEmpty()) {
            viewModelScope.launch {
                _records.postValue(
                    getRecordsByMonthUseCase.execute(
                        date.year(),
                        date.month()
                    )
                )
            }
        }
    }

    suspend fun deleteItems(records: List<Record>) {
        deleteRecordsUseCase.execute(records)
    }
}