package com.woowahan.accountbook.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.domain.accountUseCase.GetIncomeCategoryUseCase
import com.woowahan.domain.accountUseCase.GetSpendingCategoryUseCase
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSpendingCategoryUseCase: GetSpendingCategoryUseCase,
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

    fun getSettings() {
        viewModelScope.launch {
            getSpendingCategory()
            getIncomeCategory()
        }
    }

    private suspend fun getSpendingCategory() {
        _spending.postValue(getSpendingCategoryUseCase.execute())
    }

    private suspend fun getIncomeCategory() {
        _income.postValue(getIncomeCategoryUseCase.execute())
    }

    init {
        val dummyPayments = ArrayList<Payment>()
        dummyPayments.add(Payment("카카오 체크카드"))
        dummyPayments.add(Payment("신용카드"))
        dummyPayments.add(Payment("체크카드"))
        _payments.value = dummyPayments
    }
}