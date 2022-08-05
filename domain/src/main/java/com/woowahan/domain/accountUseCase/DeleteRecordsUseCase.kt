package com.woowahan.domain.accountUseCase

import com.woowahan.domain.model.Record
import com.woowahan.domain.repository.AccountRepository

class DeleteRecordsUseCase(private val repository: AccountRepository) {
    suspend fun execute(records: List<Record>) {
        repository.deleteRecords(records)
    }
}