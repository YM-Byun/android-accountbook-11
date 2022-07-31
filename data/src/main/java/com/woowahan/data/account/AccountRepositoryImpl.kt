package com.woowahan.data.account

import com.woowahan.domain.model.Category
import com.woowahan.domain.repository.AccountRepository

class AccountRepositoryImpl(
    private val dataSource: AccountLocalDataSource
) : AccountRepository {
    override suspend fun addPayment(name: String) {
        return dataSource.addPayment(name)
    }

    override suspend fun addIncomeCategory(name: String, color: Int) {
        return dataSource.addIncomeCategory(name, color)
    }

    override suspend fun addSpendingCategory(name: String, color: Int) {
        return dataSource.addSpendingCategory(name, color)
    }

    override suspend fun getCategory(mode: String): List<Category> {
        return dataSource.getCategory(mode)
    }
}