package com.woowahan.accountbook.ui.itemList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RecordAddViewModel() : ViewModel() {
    var date = mutableStateOf("")
    var price = mutableStateOf("")
    var payments = mutableStateOf("")
    var category = mutableStateOf("")
    var content = mutableStateOf("")


    fun init() {
        date.value = ""
        price.value = ""
        payments.value = ""
        category.value = ""
        content.value = ""
    }

    fun isValid(incomeClicked: Boolean): Boolean {
        if (date.value.isNotEmpty() && price.value.isNotEmpty()) {
            if (!incomeClicked) {
                return payments.value.isNotEmpty()
            }
            return true
        }
        return false
    }
}
