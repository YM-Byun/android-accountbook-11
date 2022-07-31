package com.woowahan.data.account

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment

interface AccountLocalDataSource {
    suspend fun addPayment(name: String)
    suspend fun addIncomeCategory(name: String, color: Int)
    suspend fun addSpendingCategory(name: String, color: Int)
    suspend fun getCategory(mode: String): List<Category>
    suspend fun getPayments(): List<Payment>
}