package com.woowahan.data.entity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    appContext: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(appContext, name, factory, version) {
    companion object {
        val SPENDING = "spending"
        val INCOME = "income"
    }

    val readable = readableDatabase
    val wriable = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        val createPaymentsTable = "create table payment (" +
                "id integer primary key autoincrement," +
                "name varchar(40)" +
                ")"
        db?.execSQL(createPaymentsTable)
        val paymentDefault = ContentValues()
        paymentDefault.put("id", 0)
        paymentDefault.put("name", "미분류")
        db?.insert("payment", null, paymentDefault)

        val createRecordTypeTable = "create table record_type(" +
                "name varchar(20) primary key" +
                ")"
        db?.execSQL(createRecordTypeTable)

        val createCategoryTable = "create table category(" +
                "id integer primary key autoincrement," +
                "name varchar(40)," +
                "record_type varchar(20)," +
                "color integer," +
                "foreign key (`record_type`) references `record_type` (`name`)" +
                ")"
        db?.execSQL(createCategoryTable)

        val incomeDefault = ContentValues()
        incomeDefault.put("name", "미분류")
        incomeDefault.put("color", 0)
        incomeDefault.put("record_type", INCOME)

        val spendingDefault = ContentValues()
        spendingDefault.put("name", "미분류")
        spendingDefault.put("color", 0)
        spendingDefault.put("record_type", SPENDING)
        db?.insert("category", null, incomeDefault)
        db?.insert("category", null, spendingDefault)

        val createRecordTable = "create table record(" +
                "id integer primary key autoincrement," +
                "date date," +
                "price bigint," +
                "content varchar(100)," +
                "payment_id varchar(40)," +
                "category_id varchar(40)," +
                "foreign key(`payment_id`) references `payment`(`id`)," +
                "foreign key(`category_id`) references `category`(`id`)" +
                ")"
        db?.execSQL(createRecordTable)

        val insertIncomeType = "insert into record_type(`name`) values('$INCOME')"
        db?.execSQL(insertIncomeType)

        val insertSpendingType = "insert into record_type(`name`) values('$SPENDING')"
        db?.execSQL(insertSpendingType)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}