package com.woowahan.data.account

import android.R.attr
import android.annotation.SuppressLint
import android.content.ContentValues
import com.woowahan.data.entity.CategoryData
import com.woowahan.data.entity.DBHelper
import com.woowahan.data.entity.toModel
import com.woowahan.domain.model.Category


class AccountLocalDataSourceImpl(
    private val dbHelper: DBHelper
) :
    AccountLocalDataSource {
    override suspend fun addPayment(name: String) {
        val sqlInsertPayment = "insert into payments(`name`) values('$name')"
        dbHelper.wriable.execSQL(sqlInsertPayment)
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

    @SuppressLint("Range")
    override suspend fun getCategory(mode: String): List<Category> {
        val query = "select * from category where record_type = '${mode}'"
        val categories = ArrayList<Category>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val color = cursor.getInt(cursor.getColumnIndex("color"))
            categories.add(CategoryData(name, color).toModel())
        }

        return categories

    }
}