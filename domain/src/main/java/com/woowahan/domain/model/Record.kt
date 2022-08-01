package com.woowahan.domain.model

data class Record(
    val id: Int,
    val date: String,
    val price: Long,
    val type: String,
    val payment: Payment,
    val content: String,
    val category: Category
) {
}