package com.woowahan.data.account

interface AccountLocalDataSource {
    suspend fun addPayment(name: String)
    suspend fun addIncomeCategory(name: String, color: Int)
    suspend fun addSpendingCategory(name: String, color: Int)
}