package com.woowahan.data.entity

import com.woowahan.domain.model.Payment

data class PaymentData(
    val name: String
)

fun PaymentData.toModel(): Payment {
    return Payment(this.name)
}