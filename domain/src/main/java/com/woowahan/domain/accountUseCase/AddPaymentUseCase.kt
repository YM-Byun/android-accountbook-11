package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class AddPaymentUseCase(private val repository: AccountRepository) {
    suspend fun execute(name: String) {
        repository.addPayment(name)
    }
}