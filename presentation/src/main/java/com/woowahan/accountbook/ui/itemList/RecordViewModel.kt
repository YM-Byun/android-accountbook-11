package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase
) : ViewModel() {
    private val _records = MutableLiveData<List<Record>>(emptyList())
    val records: LiveData<List<Record>>
        get() = _records

    val leftBtnOnClick = MutableLiveData(true)
    val rightBtnOnClick = MutableLiveData(true)

    fun getRecords(date: String) {
        if (date.isNotEmpty()) {
            val token = date.replace("년", "").replace("월", "").replace("일", "").split(" ")
            viewModelScope.launch {
                _records.postValue(
                    getRecordsByMonthUseCase.execute(
                        token[0].toInt(),
                        token[1].toInt()
                    )
                )
            }
        }
    }
}