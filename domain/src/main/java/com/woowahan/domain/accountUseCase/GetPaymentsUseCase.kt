package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class GetPaymentsUseCase(private val repository: AccountRepository) {
    suspend fun execute() =
        repository.getPayments()
}