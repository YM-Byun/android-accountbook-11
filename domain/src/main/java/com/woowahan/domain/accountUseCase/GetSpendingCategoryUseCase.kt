package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class GetSpendingCategoryUseCase(private val repository: AccountRepository) {
    suspend fun execute() =
        repository.getCategory("spending")
}