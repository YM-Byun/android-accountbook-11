package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.domain.accountUseCase.GetRecordsUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getRecordsUseCase: GetRecordsUseCase
) : ViewModel() {
    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records

    val leftBtnOnClick = MutableLiveData(true)
    val rightBtnOnClick = MutableLiveData(true)

    fun getRecords() {
        viewModelScope.launch {
            _records.postValue(getRecordsUseCase.execute())
        }
    }
}