package com.woowahan.data.entity

import com.woowahan.domain.model.Payment

data class PaymentData(
    val id: Int,
    val name: String
)

fun PaymentData.toModel(): Payment {
    return Payment(this.id, this.name)
}