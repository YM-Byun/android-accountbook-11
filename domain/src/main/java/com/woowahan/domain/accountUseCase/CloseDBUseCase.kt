package com.woowahan.domain.accountUseCase

import com.woowahan.domain.model.Record
import com.woowahan.domain.repository.AccountRepository

class CloseDBUseCase(private val repository: AccountRepository) {
    suspend fun execute() {
        repository.closeDB()
    }
}