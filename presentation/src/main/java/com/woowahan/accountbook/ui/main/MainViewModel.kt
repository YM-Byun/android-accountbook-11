package com.woowahan.accountbook.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowahan.accountbook.ui.navigate.ITEM_LIST
import com.woowahan.accountbook.ui.navigate.SETTINGS
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
        when (type) {
            "prev" -> {
                calendar.add(Calendar.MONTH, -1)
                _currentScreen.value = "${year}년 ${month + 1}월"
            }
            "next" -> {
                calendar.add(Calendar.MONTH, 1)
                _currentScreen.value = "${year}년 ${month + 1}월"
            }
            SETTINGS -> {
                _currentScreen.value = "설정"
            }
            ITEM_LIST -> {
                _currentScreen.value = "${year}년 ${month + 1}월"
            }
        }
    }
}