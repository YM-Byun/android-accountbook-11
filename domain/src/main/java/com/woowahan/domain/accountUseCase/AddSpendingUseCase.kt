package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class AddSpendingUseCase(private val repository: AccountRepository) {
    suspend fun execute(name: String, color: Int) {
        repository.addSpendingCategory(name, color)
    }
}