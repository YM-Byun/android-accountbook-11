package com.woowahan.data.account

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record
import com.woowahan.domain.repository.AccountRepository

class AccountRepositoryImpl(
    private val dataSource: AccountLocalDataSource
) : AccountRepository {
    override suspend fun addPayment(name: String) {
        return dataSource.addPayment(name)
    }

    override suspend fun addCategory(name: String, color: Int, mode: String) {
        return dataSource.addCategory(name, color, mode)
    }

    override suspend fun getCategory(mode: String): List<Category> {
        return dataSource.getCategory(mode)
    }

    override suspend fun getPayments(): List<Payment> {
        return dataSource.getPayments()
    }

    override suspend fun addRecord(mode: String, record: Record) {
        return dataSource.addRecord(mode, record)
    }

    override suspend fun getRecordsByMonth(year: Int, month: Int): List<Record> {
        return dataSource.getRecords(year, month)
    }

    override suspend fun deleteRecords(records: List<Record>) {
        return dataSource.deleteRecords(records)
    }
}