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

class MainViewModel : ViewModel() {
    private val calendar = Calendar.getInstance()

    private val _appBarTitle = MutableLiveData("${year}년 ${month + 1}월")
    val appBarTitle: LiveData<String>
        get() = _appBarTitle

    private val month
        get() = calendar.get(Calendar.MONTH)
    private val year
        get() = calendar.get(Calendar.YEAR)

    fun onNextClicked() {
        if (!isSameMonth(year, month)) {
            calendar.add(Calendar.MONTH, 1)
            _appBarTitle.value = generateTitle(year, month + 1)
        }
    }

    fun onPrevClicked() {
        calendar.add(Calendar.MONTH, -1)
        _appBarTitle.value = generateTitle(year, month + 1)
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
    }
}