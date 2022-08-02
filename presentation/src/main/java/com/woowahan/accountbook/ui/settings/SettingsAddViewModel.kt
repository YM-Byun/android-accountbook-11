package com.woowahan.accountbook.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.accountUseCase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsAddViewModel @Inject constructor(
    private val addPaymentUseCase: AddPaymentUseCase,
    private val addIncomeCategoryUseCase: AddIncomeCategoryUseCase,
    private val addSpendingUseCase: AddSpendingUseCase
) : ViewModel() {
    var name = mutableStateOf("")
    var selectedColorIdx = mutableStateOf(0)

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
            spendingColors
        } else {
            incomeColors
        }
    }

    fun isValid(): Boolean {
        return (name.value.trim().isNotEmpty() && name.value.trim() != "미분류")
    }

    fun init() {
        name.value = ""
        selectedColorIdx.value = 0
    }
}