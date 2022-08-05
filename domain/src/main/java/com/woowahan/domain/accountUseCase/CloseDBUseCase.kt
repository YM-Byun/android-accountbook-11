package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class CloseDBUseCase(private val repository: AccountRepository) {
    fun execute() {
        repository.closeDB()
    }
}