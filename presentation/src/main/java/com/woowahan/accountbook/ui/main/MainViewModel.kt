package com.woowahan.accountbook.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.navigate.ITEM_LIST
import com.woowahan.accountbook.ui.navigate.SETTINGS
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase
) : ViewModel() {
    private val calendar = Calendar.getInstance()
    private val month
        get() = calendar.get(Calendar.MONTH)
    private val year
        get() = calendar.get(Calendar.YEAR)

    private val _appBarTitle = MutableLiveData("${year}년 ${month + 1}월")
    val appBarTitle: LiveData<String>
        get() = _appBarTitle

    private val _records = MutableLiveData<List<Record>>(emptyList())
    val records: LiveData<List<Record>>
        get() = _records


    fun onScreenChange(type: String) {
        when (type) {
            "prev" -> {
                calendar.add(Calendar.MONTH, -1)
                _appBarTitle.value = "${year}년 ${month + 1}월"
            }
            "next" -> {
                if (!isSameMonth(year, month)) {
                    calendar.add(Calendar.MONTH, 1)
                    _appBarTitle.value = "${year}년 ${month + 1}월"
                }
            }
            SETTINGS -> {
                _appBarTitle.value = "설정"
            }
            ITEM_LIST -> {
                _appBarTitle.value = "${year}년 ${month + 1}월"
            }
        }
    }

    private fun isSameMonth(year: Int, month: Int): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        return currentYear == year && currentMonth == month
    }

    fun onDatePicked(newYear: Int, newMonth: Int) {
        _appBarTitle.value = "${newYear}년 ${newMonth}월"
    }

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
}