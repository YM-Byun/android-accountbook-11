package com.woowahan.data.account

import android.R.attr
import android.annotation.SuppressLint
import android.content.ContentValues
import com.woowahan.data.entity.*
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record


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

    @SuppressLint("Range")
    override suspend fun getPayments(): List<Payment> {
        val query = "select * from payments"
        val payments = ArrayList<Payment>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            payments.add(PaymentData(name).toModel())
        }

        return payments
    }

    override suspend fun addRecord(mode: String, record: Record) {
        val values = ContentValues()
        values.put("date", "${record.year}-${record.month}-${record.day}")
        values.put("price", record.price)
        values.put("content", record.content)
        values.put("payments", record.payment.name)
        values.put("category", record.category.name)
        values.put("record_type", mode)

        dbHelper.wriable.insert("record", null, values)
    }

    @SuppressLint("Range")
    override suspend fun getRecords(): List<Record> {
        val query = "select * from record " +
                "inner join category on record.`category` = category.`name`"
        val records = ArrayList<Record>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val price = cursor.getLong(cursor.getColumnIndex("price"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val payment = cursor.getString(cursor.getColumnIndex("payments"))
            val category = cursor.getString(cursor.getColumnIndex("category"))
            val recordType = cursor.getString(cursor.getColumnIndex("record_type"))
            val color = cursor.getInt(cursor.getColumnIndex("color"))

            val record = RecordData(id, date, price, content, payment, category, recordType, color)
            records.add(record.toModel())
        }

        return records
    }
}