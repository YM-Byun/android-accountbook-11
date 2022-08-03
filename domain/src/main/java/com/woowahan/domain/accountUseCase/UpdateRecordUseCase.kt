package com.woowahan.domain.accountUseCase

import com.woowahan.domain.model.Record
import com.woowahan.domain.repository.AccountRepository

class UpdateRecordUseCase(private val repository: AccountRepository) {
    suspend fun execute(record: Record) =
        repository.updateRecord(record)
}