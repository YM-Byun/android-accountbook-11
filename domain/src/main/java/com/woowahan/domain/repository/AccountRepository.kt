package com.woowahan.domain.repository

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun addPayment(name: String)
    suspend fun addIncomeCategory(name: String, color: Int)
    suspend fun addSpendingCategory(name: String, color: Int)
}