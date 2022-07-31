package com.woowahan.domain.repository

import com.woowahan.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun addPayment(name: String)
    suspend fun addIncomeCategory(name: String, color: Int)
    suspend fun addSpendingCategory(name: String, color: Int)

    suspend fun getIncomeCategory(): List<Category>
}