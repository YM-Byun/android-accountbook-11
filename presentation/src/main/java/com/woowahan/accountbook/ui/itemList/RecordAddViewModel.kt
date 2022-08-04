package com.woowahan.accountbook.ui.itemList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woowahan.accountbook.extenstion.day
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
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
    private var id by mutableStateOf(0)
    var date by mutableStateOf("")
    var price by mutableStateOf("")
    var payment by mutableStateOf(Payment(0, ""))
    var category by mutableStateOf(Category(0, "", 0))
    var content by mutableStateOf("")

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
        date = ""
        price = ""
        payment.name = ""
        category.name = ""
        content = ""
    }

    fun isValid(incomeClicked: Boolean): Boolean {
        if (date.trim().isNotEmpty() && price.trim().isNotEmpty()) {
            if (!incomeClicked) {
                return payment.name.isNotEmpty()
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
        val amount = if (mode == DBHelper.INCOME) {
            price.toLong()
        } else {
            (price.toLong()) * -1
        }

        if (category.name.isEmpty()) {
            if (mode == DBHelper.INCOME) {
                category.id = 1
            } else {
                category.id = 2
            }
            category.name = "미분류"
        }
        return Record(
            id,
            date.year(),
            date.month(),
            date.day(),
            amount,
            mode,
            payment,
            content,
            category
        )
    }

    fun loadRecord(record: Record) {
        this.id = record.id
        this.date = "${record.year}년 ${record.month}월 ${record.day}일"
        this.price = record.price.toString()
        this.payment = record.payment.copy()
        this.category = record.category.copy()
        this.content = record.content
    }
}
