package com.woowahan.domain.accountUseCase

import com.woowahan.domain.model.Category
import com.woowahan.domain.repository.AccountRepository

class UpdateCategoryUseCase(private val repository: AccountRepository) {
    suspend fun execute(category: Category) =
        repository.updateCategory(category)
}