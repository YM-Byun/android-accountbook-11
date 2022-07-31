package com.woowahan.data.entity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    appContext: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(appContext, name, factory, version) {
    val readable = readableDatabase
    val wriable = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        val createPaymentsTable = "create table payments (" +
                "name varchar(40) primary key" +
                ")"
        db?.execSQL(createPaymentsTable)

        val createRecordTypeTable = "create table record_type(" +
                "name varchar(20) primary key" +
                ")"
        db?.execSQL(createRecordTypeTable)

        val createCategoryTable = "create table category(" +
                "name varchar(40)," +
                "record_type varchar(20)," +
                "foreign key (`record_type`) references `record_type` (`name`)," +
                "primary key(`name`, `record_type`)" +
                ")"
        db?.execSQL(createCategoryTable)

        val createRecordTable = "create table record(" +
                "id integer primary key autoincrement," +
                "date date," +
                "price bigint," +
                "content varchar(100)," +
                "payments varchar(40)," +
                "category varchar(40)," +
                "record_type varchar(20)," +
                "foreign key(`payments`) references `payments`(`name`)," +
                "foreign key(`category`, `record_type`) references `category`(`name`, `record_type`)" +
                ")"
        db?.execSQL(createRecordTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}