package com.woowahan.accountbook.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.domain.accountUseCase.GetIncomeCategoryUseCase
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getIncomeCategoryUseCase: GetIncomeCategoryUseCase
) : ViewModel() {
    private val _payments = MutableLiveData<List<Payment>>()
    val payments: LiveData<List<Payment>>
        get() = _payments

    private val _spending = MutableLiveData<List<Category>>()
    val spending: LiveData<List<Category>>
        get() = _spending

    private val _income = MutableLiveData<List<Category>>()
    val income: LiveData<List<Category>>
        get() = _income

    fun getIncomeCategory() {
        viewModelScope.launch {
            _income.postValue(getIncomeCategoryUseCase.execute())
        }
    }

    init {
        val dummyPayments = ArrayList<Payment>()
        dummyPayments.add(Payment("카카오 체크카드"))
        dummyPayments.add(Payment("신용카드"))
        dummyPayments.add(Payment("체크카드"))
        _payments.value = dummyPayments

        val dummySpending = ArrayList<Category>()
        dummySpending.add(Category("교통", 0))
        dummySpending.add(Category("문화/여가", 0))
        dummySpending.add(Category("미분류", 0))
        dummySpending.add(Category("생활", 0))
        dummySpending.add(Category("쇼팡/뷰티", 0))
        dummySpending.add(Category("식비", 0))
        _spending.value = dummySpending
    }
}