package com.woowahan.domain.repository

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record

interface AccountRepository {
    suspend fun addPayment(name: String)
    suspend fun addIncomeCategory(name: String, color: Int)
    suspend fun addSpendingCategory(name: String, color: Int)

    suspend fun getCategory(mode: String): List<Category>
    suspend fun getPayments(): List<Payment>

    suspend fun addRecord(mode: String, record: Record)
    suspend fun getRecords(): List<Record>
}