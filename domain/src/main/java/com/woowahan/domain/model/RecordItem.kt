package com.woowahan.domain.model

data class RecordItem(
    val id: Int,
    val type: String,
    val title: String,
    val payment: String,
    val amount: String
) {
}