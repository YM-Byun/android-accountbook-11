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
        val sqlInsertPayment = "insert into payment(`name`) values('$name')"
        dbHelper.wriable.execSQL(sqlInsertPayment)
    }

    override suspend fun addCategory(name: String, color: Int, mode: String) {
        val values = ContentValues()
        values.put("name", name)
        values.put("color", color)
        values.put("record_type", mode)
        dbHelper.wriable.insert("category", null, values)
    }

    @SuppressLint("Range")
    override suspend fun getCategory(mode: String): List<Category> {
        val query = "select * from category where record_type = '${mode}'"
        val categories = ArrayList<Category>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val color = cursor.getInt(cursor.getColumnIndex("color"))

            categories.add(CategoryData(id, name, color).toModel())
        }

        return categories
    }

    @SuppressLint("Range")
    override suspend fun getPayments(): List<Payment> {
        val query = "select * from payment"
        val payments = ArrayList<Payment>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))

            if (name.isNotEmpty()) {
                payments.add(PaymentData(id, name).toModel())
            }
        }

        return payments
    }

    override suspend fun addRecord(mode: String, record: Record) {
        val values = ContentValues()
        values.put(
            "date",
            "${record.year}-${String.format("%02d", record.month)}-${
                String.format(
                    "%02d",
                    record.day
                )
            }"
        )
        values.put("price", record.price)
        values.put("content", record.content)
        values.put("payment_id", record.payment.id)
        values.put("category_id", record.category.id)

        dbHelper.wriable.insert("record", null, values)
    }

    @SuppressLint("Range")
    override suspend fun getRecords(year: Int, month: Int): List<Record> {
        val query = "select *, payment.`name` as `payment`, category.`name` as `category` " +
                " from record " +
                "inner join category on record.`category_id` = category.`id` " +
                "inner join payment on record.`payment_id` = payment.`id` " +
                "where strftime('%m', date) = '${String.format("%02d", month)}' " +
                "and strftime('%Y', date) = '$year'"
        val records = ArrayList<Record>()

        val cursor = dbHelper.readable.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val price = cursor.getLong(cursor.getColumnIndex("price"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val paymentId = cursor.getInt(cursor.getColumnIndex("payment_id"))
            val payment = cursor.getString(cursor.getColumnIndex("payment"))
            val categoryId = cursor.getInt(cursor.getColumnIndex("category_id"))
            val category = cursor.getString(cursor.getColumnIndex("category"))
            val recordType = cursor.getString(cursor.getColumnIndex("record_type"))
            val color = cursor.getInt(cursor.getColumnIndex("color"))

            val record =
                RecordData(
                    id,
                    date,
                    price,
                    content,
                    paymentId,
                    payment,
                    categoryId,
                    category,
                    recordType,
                    color
                )
            records.add(record.toModel())
        }

        return records
    }

    override suspend fun deleteRecords(records: List<Record>) {
        for (record in records) {
            dbHelper.wriable.delete("record", "id=?", arrayOf(record.id.toString()))
        }
    }
}