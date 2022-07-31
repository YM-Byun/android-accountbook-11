package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class GetIncomeCategoryUseCase(private val repository: AccountRepository) {
    suspend fun execute() =
        repository.getIncomeCategory()
}