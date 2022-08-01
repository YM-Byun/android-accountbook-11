package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class AddIncomeCategoryUseCase(private val repository: AccountRepository) {
    suspend fun execute(name: String, color: Int) {
        repository.addIncomeCategory(name, color)
    }
}