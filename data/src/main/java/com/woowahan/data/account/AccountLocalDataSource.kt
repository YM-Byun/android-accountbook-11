package com.woowahan.data.account

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record

interface AccountLocalDataSource {
    suspend fun addPayment(name: String)
    suspend fun addCategory(name: String, color: Int, mode: String)
    suspend fun getCategory(mode: String): List<Category>
    suspend fun getPayments(): List<Payment>

    suspend fun addRecord(mode: String, record: Record)
    suspend fun getRecords(year: Int, month: Int): List<Record>

    suspend fun deleteRecords(records: List<Record>)
}