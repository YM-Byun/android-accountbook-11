package com.woowahan.accountbook.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val calendar = Calendar.getInstance()
    private val month
        get() = calendar.get(Calendar.MONTH)
    private val year
        get() = calendar.get(Calendar.YEAR)

    private val _currentScreen = MutableLiveData("${year}년 ${month + 1}월")
    val currentScreen: LiveData<String>
        get() = _currentScreen


    fun onScreenChange(type: String) {
        if (type == "prev") {
            calendar.add(Calendar.MONTH, -1)
        } else {
            calendar.add(Calendar.MONTH, 1)
        }

        _currentScreen.value = "${year}년 ${month + 1}월"
    }
}