package com.woowahan.domain.repository

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record

interface AccountRepository {
    suspend fun addPayment(name: String)
    suspend fun addCategory(name: String, color: Int, mode: String)

    suspend fun getCategory(mode: String): List<Category>
    suspend fun getPayments(): List<Payment>

    suspend fun addRecord(mode: String, record: Record)
    suspend fun getRecordsByMonth(year: Int, month: Int): List<Record>

    suspend fun updateRecord(record: Record)
    suspend fun updateCategory(category: Category)
    suspend fun updatePayment(payment: Payment)

    suspend fun deleteRecords(records: List<Record>)

    fun closeDB()
}