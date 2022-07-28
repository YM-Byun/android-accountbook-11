package com.woowahan.accountbook.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment

class SettingsViewModel : ViewModel() {
    private val _payments = MutableLiveData<List<Payment>>()
    val payments: LiveData<List<Payment>>
        get() = _payments

    private val _spending = MutableLiveData<List<Category>>()
    val spending: LiveData<List<Category>>
        get() = _spending

    private val _income = MutableLiveData<List<Category>>()
    val income: LiveData<List<Category>>
        get() = _income

    init {
        val dummyPayments = ArrayList<Payment>()
        dummyPayments.add(Payment("카카오 체크카드"))
        dummyPayments.add(Payment("신용카드"))
        dummyPayments.add(Payment("체크카드"))
        _payments.value = dummyPayments

        val dummySpending = ArrayList<Category>()
        dummySpending.add(Category("교통"))
        dummySpending.add(Category("문화/여가"))
        dummySpending.add(Category("미분류"))
        dummySpending.add(Category("생활"))
        dummySpending.add(Category("쇼팡/뷰티"))
        dummySpending.add(Category("식비"))
        _spending.value = dummySpending

        val dummyIncome = ArrayList<Category>()
        dummyIncome.add(Category("월급"))
        dummyIncome.add(Category("용돈"))
        dummyIncome.add(Category("기타"))
        _income.value = dummyIncome
    }
}