package com.woowahan.accountbook.ui.itemList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.accountUseCase.*
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordAddViewModel @Inject constructor(
    private val addIncomeRecordUseCase: AddIncomeRecordUseCase,
    private val addSpendingRecordUseCase: AddSpendingRecordUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase,
    private val getSpendingCategoryUseCase: GetSpendingCategoryUseCase,
    private val getIncomeCategoryUseCase: GetIncomeCategoryUseCase
) : ViewModel() {
    var date = mutableStateOf("")
    var price = mutableStateOf("")
    var payment = mutableStateOf(Payment(""))
    var category = mutableStateOf(Category("", 0))
    var content = mutableStateOf("")

    private val _payments = MutableLiveData<List<Payment>>(emptyList())
    val payments: LiveData<List<Payment>>
        get() = _payments

    private val _spending = MutableLiveData<List<Category>>(emptyList())
    val spending: LiveData<List<Category>>
        get() = _spending

    private val _income = MutableLiveData<List<Category>>(emptyList())
    val income: LiveData<List<Category>>
        get() = _income


    fun init() {
        date.value = ""
        price.value = ""
        payment.value.name = ""
        category.value.name = ""
        content.value = ""
    }

    fun isValid(incomeClicked: Boolean): Boolean {
        if (date.value.trim().isNotEmpty() && price.value.trim().isNotEmpty()) {
            if (!incomeClicked) {
                return payment.value.name.isNotEmpty()
            }
            return true
        }
        return false
    }

    suspend fun addSpendingRecord() {
        addSpendingRecordUseCase.execute(getNewRecord(DBHelper.SPENDING))
        init()
    }

    suspend fun addIncomeRecord() {
        addIncomeRecordUseCase.execute(getNewRecord(DBHelper.INCOME))
        init()
    }

    fun getOptions() {
        viewModelScope.launch {
            getPayments()
            getSpendingCategory()
            getIncomeCategory()
        }
    }

    private suspend fun getPayments() {
        _payments.postValue(getPaymentsUseCase.execute())
    }

    private suspend fun getSpendingCategory() {
        _spending.postValue(getSpendingCategoryUseCase.execute())
    }

    private suspend fun getIncomeCategory() {
        _income.postValue(getIncomeCategoryUseCase.execute())
    }

    private fun getNewRecord(mode: String): Record {
        val tokens = date.value.replace("년", "").replace("월", "").replace("일", "").split(" ")
        val amount = if (mode == DBHelper.INCOME) {
            price.value.toLong()
        } else {
            (price.value.toLong()) * -1
        }

        if (category.value.name.isEmpty()) {
            category.value.name = "미분류"
        }
        return Record(
            0,
            tokens[0].toInt(),
            tokens[1].toInt(),
            tokens[2].toInt(),
            amount,
            mode,
            payment.value,
            content.value,
            category.value
        )
    }
}
