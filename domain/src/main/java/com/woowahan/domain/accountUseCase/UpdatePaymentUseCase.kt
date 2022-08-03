package com.woowahan.domain.accountUseCase

import com.woowahan.domain.model.Payment
import com.woowahan.domain.repository.AccountRepository

class UpdatePaymentUseCase(private val repository: AccountRepository) {
    suspend fun execute(payment: Payment) =
        repository.updatePayment(payment)
}