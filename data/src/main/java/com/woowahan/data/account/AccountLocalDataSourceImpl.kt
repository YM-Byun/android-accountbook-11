package com.woowahan.data.account

import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.repository.AccountRepository

class AccountLocalDataSourceImpl(
    private val dbHelper: DBHelper
) :
    AccountLocalDataSource {
    override suspend fun addPayment(name: String) {
        val sqlInsertPayment = "insert into payments values(`$name`)"
        dbHelper.wriable.use {
            it.execSQL(sqlInsertPayment)
        }
    }

    override suspend fun addIncomeCategory(name: String, color: Int) {
    }

    override suspend fun addSpendingCategory(name: String, color: Int) {
    }

}