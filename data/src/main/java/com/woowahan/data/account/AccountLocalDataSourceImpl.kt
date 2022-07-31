package com.woowahan.data.account

import android.R.attr
import android.content.ContentValues
import com.woowahan.data.entity.DBHelper


class AccountLocalDataSourceImpl(
    private val dbHelper: DBHelper
) :
    AccountLocalDataSource {
    override suspend fun addPayment(name: String) {
        val sqlInsertPayment = "insert into payments(`name`) values('$name')"
        dbHelper.wriable.use {
            it.execSQL(sqlInsertPayment)
        }
    }

    override suspend fun addIncomeCategory(name: String, color: Int) {
        val values = ContentValues()
        values.put("name", name)
        values.put("color", color)
        values.put("record_type", DBHelper.INCOME)
        dbHelper.wriable.insert("category", null, values)
    }

    override suspend fun addSpendingCategory(name: String, color: Int) {
        val values = ContentValues()
        values.put("name", name)
        values.put("color", color)
        values.put("record_type", DBHelper.SPENDING)
        dbHelper.wriable.insert("category", null, values)
    }

}