package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class GetRecordsUseCase(private val repository: AccountRepository) {
    suspend fun execute() =
        repository.getRecords()
}