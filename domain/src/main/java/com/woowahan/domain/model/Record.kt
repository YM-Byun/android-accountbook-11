package com.woowahan.domain.model

data class Record(
    val id: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val price: Long,
    val type: String,
    val payment: Payment,
    val content: String,
    val category: Category
) {
}