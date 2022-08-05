package com.woowahan.domain.model

data class Calendar(
    val day: Int,
    val income: String,
    val spending: String,
    val total: String,
    val isToday: Boolean,
    val isCurrentMonth: Boolean,
)