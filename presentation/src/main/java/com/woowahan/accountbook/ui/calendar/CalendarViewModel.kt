package com.woowahan.accountbook.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.accountUseCase.GetRecordsByMonthUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getRecordsByMonthUseCase: GetRecordsByMonthUseCase
) : ViewModel() {
    private val current = Calendar.getInstance()
    private val currentYear = current.get(Calendar.YEAR)
    private val currentMonth = current.get(Calendar.MONTH)
    private val currentDay = current.get(Calendar.DAY_OF_MONTH)

    private val _calendarData =
        MutableLiveData<List<com.woowahan.domain.model.Calendar>>(emptyList())
    val calendarData: LiveData<List<com.woowahan.domain.model.Calendar>>
        get() = _calendarData

    fun parseCalendar(
        date: String,
        records: List<Record>
    ) {
        val list = ArrayList<com.woowahan.domain.model.Calendar>()

        if (date.isEmpty()) {
            return
        }

        val year = date.year()
        val month = date.month() - 1

        val lastDayOfLastMonth = getLastDayOfMonth(year, month - 1)
        val lastDayOfThisMonth = getLastDayOfMonth(year, month)
        val firstDayOfWeek = getFirstDayOfWeek(year, month)
        var currentMonthDay = 1

        val recordsGroup = records.groupBy {
            it.day
        }

        currentMonthDay += addFirstLine(
            list,
            recordsGroup,
            firstDayOfWeek,
            lastDayOfLastMonth,
            year,
            month
        )

        val remainLines = ceil(((lastDayOfThisMonth - currentMonthDay) / 7f)).toInt()
        addRemainLine(
            list,
            recordsGroup,
            currentMonthDay,
            lastDayOfThisMonth,
            remainLines,
            year,
            month
        )

        _calendarData.postValue(list)
    }

    private fun getLastDayOfMonth(year: Int, month: Int): Int {
        val lastDay = Calendar.getInstance().clone() as Calendar
        lastDay.set(year, month, 1)
        return lastDay.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun addFirstLine(
        list: MutableList<com.woowahan.domain.model.Calendar>,
        recordsGroup: Map<Int, List<Record>>,
        startDay: Int,
        lastDayOfLastMonth: Int,
        selectYear: Int,
        selectMonth: Int,
    ): Int {
        var newDay = 1
        for (i in 0 until 7) {
            if (i < startDay) {
                val calendar = com.woowahan.domain.model.Calendar(
                    (lastDayOfLastMonth - startDay + i + 1),
                    "0",
                    "0",
                    "0",
                    isToday = false,
                    isCurrentMonth = false
                )
                list.add(calendar)
            } else {
                var income = 0L
                var spending = 0L

                recordsGroup[newDay]?.forEach {
                    if (it.type == DBHelper.INCOME) {
                        income += it.price
                    } else {
                        spending += it.price
                    }
                }

                val calendar = com.woowahan.domain.model.Calendar(
                    newDay,
                    String.format("%,d", income),
                    String.format("%,d", spending),
                    String.format("%,d", income + spending),
                    isToday = isToday(selectYear, selectMonth, newDay),
                    isCurrentMonth = true
                )

                list.add(calendar)

                newDay += 1
            }
        }
        return newDay - 1
    }

    private fun addRemainLine(
        list: MutableList<com.woowahan.domain.model.Calendar>,
        recordsGroup: Map<Int, List<Record>>,
        startDay: Int,
        lastDayOfThisMonth: Int,
        remainLine: Int,
        year: Int,
        month: Int
    ) {
        var day = startDay

        for (line in 0 until remainLine * 7) {
            if (day > lastDayOfThisMonth) {
                val calendar = com.woowahan.domain.model.Calendar(
                    (day - lastDayOfThisMonth),
                    "0",
                    "0",
                    "0",
                    isToday = false,
                    isCurrentMonth = false
                )
                list.add(calendar)
            } else {
                var income = 0L
                var spending = 0L

                recordsGroup[day]?.forEach {
                    if (it.type == DBHelper.INCOME) {
                        income += it.price
                    } else {
                        spending += it.price
                    }
                }

                val calendar = com.woowahan.domain.model.Calendar(
                    day,
                    String.format("%,d", income),
                    String.format("%,d", spending),
                    String.format("%,d", income + spending),
                    isToday = isToday(year, month, day),
                    isCurrentMonth = true
                )

                list.add(calendar)
            }
            day += 1
        }
    }

    private fun isToday(selectYear: Int, selectMonth: Int, selectDay: Int): Boolean {
        return selectYear == currentYear && selectMonth == currentMonth && selectDay == currentDay
    }

    private fun getFirstDayOfWeek(year: Int, month: Int): Int {
        val firstDay = Calendar.getInstance().clone() as Calendar
        firstDay.set(year, month, 1)
        return firstDay.get(Calendar.DAY_OF_WEEK) - 1
    }

    fun getCalendarData(date: String) {
        if (date.isNotEmpty()) {
            viewModelScope.launch {
                parseCalendar(
                    date,
                    getRecordsByMonthUseCase.execute(
                        date.year(),
                        date.month()
                    )
                )
            }
        }
    }
}