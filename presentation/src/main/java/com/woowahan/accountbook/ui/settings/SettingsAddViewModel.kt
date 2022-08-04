package com.woowahan.accountbook.ui.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.accountUseCase.*
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsAddViewModel @Inject constructor(
    private val addPaymentUseCase: AddPaymentUseCase,
    private val addIncomeCategoryUseCase: AddIncomeCategoryUseCase,
    private val addSpendingUseCase: AddSpendingCategoryUseCase
) : ViewModel() {
    private var id by mutableStateOf(0)
    var name by mutableStateOf("")
    var selectedColorIdx by mutableStateOf(0)

    suspend fun addPayment(name: String) {
        addPaymentUseCase.execute(name)
        init()
    }

    suspend fun addIncomeCategory(name: String, color: Int) {
        addIncomeCategoryUseCase.execute(name, color)
        init()
    }

    suspend fun addSpendingCategory(name: String, color: Int) {
        addSpendingUseCase.execute(name, color)
        init()
    }

    fun getColors(mode: String): List<Color> {
        return if (mode == ADD_SPENDING) {
            SpendingColors
        } else {
            IncomeColors
        }
    }

    fun loadCategory(category: Category) {
        this.id = category.id
        this.name = category.name
        this.selectedColorIdx = category.color
    }

    fun loadPayment(payment: Payment) {
        this.id = payment.id
        this.name = payment.name
    }

    fun isValid(): Boolean {
        return (name.trim().isNotEmpty() && name.trim() != "미분류")
    }

    fun init() {
        id = 0
        name = ""
        selectedColorIdx = 0
    }
}