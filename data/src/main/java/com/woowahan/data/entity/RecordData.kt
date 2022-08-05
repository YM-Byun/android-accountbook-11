package com.woowahan.data.entity

import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record

data class RecordData(
    val id: Int,
    val date: String,
    val price: Long,
    val content: String,
    val payment_id: Int,
    val payment: String,
    val category_id: Int,
    val category: String,
    val recordType: String,
    val color: Int
)

fun RecordData.toModel(): Record {
    val tokens = this.date.split("-")

    return Record(
        this.id,
        tokens[0].toInt(),
        tokens[1].toInt(),
        tokens[2].toInt(),
        this.price,
        this.recordType,
        Payment(this.payment_id, this.payment),
        this.content,
        Category(this.category_id, this.category, this.color)
    )
}