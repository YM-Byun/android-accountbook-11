package com.woowahan.accountbook.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase,
) : ViewModel() {
    private val calendar = Calendar.getInstance()

    /** Variable for date and title */
    private val _appBarTitle = MutableLiveData("${year}년 ${month + 1}월")
    val appBarTitle: LiveData<String>
        get() = _appBarTitle

    private val month
        get() = calendar.get(Calendar.MONTH)
    private val year
        get() = calendar.get(Calendar.YEAR)


    /** Variable for records */
    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records

    private var _totalIncome = 0L
    val totalIncome: Long
        get() = _totalIncome

    private var _totalSpending = 0L
    val totalSpending: Long
        get() = _totalSpending

    val absTotalSpending: Long
        get() = (totalSpending * -1)

    val totalAmount: Long
        get() = totalIncome + totalSpending

    fun onNextClicked() {
        if (!isSameMonth(year, month)) {
            calendar.add(Calendar.MONTH, 1)
            _appBarTitle.value = generateTitle(year, month + 1)
            getRecords()
        }
    }

    fun onPrevClicked() {
        calendar.add(Calendar.MONTH, -1)
        _appBarTitle.value = generateTitle(year, month + 1)
        getRecords()
    }

    private fun generateTitle(year: Int, month: Int): String {
        return "${year}년 ${month}월"
    }

    private fun isSameMonth(year: Int, month: Int): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        return currentYear == year && currentMonth == month
    }

    fun onDatePicked(newYear: Int, newMonth: Int) {
        _appBarTitle.value = generateTitle(newYear, newMonth)
        calendar.set(newYear, newMonth - 1, calendar.get(Calendar.DAY_OF_MONTH))
        getRecords()
    }

    fun getRecords() {
        viewModelScope.launch {
            var recordList = getRecordsByMonthUseCase.execute(year, month + 1)

            _totalIncome = recordList.filter { it.type == DBHelper.INCOME }.sumOf { it.price }
            _totalSpending =
                recordList.filter { it.type == DBHelper.SPENDING }.sumOf { it.price }

            _records.postValue(recordList)
        }
    }
}