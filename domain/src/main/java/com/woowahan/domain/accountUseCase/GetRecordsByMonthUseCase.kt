package com.woowahan.domain.accountUseCase

import com.woowahan.domain.repository.AccountRepository

class GetRecordsByMonthUseCase(private val repository: AccountRepository) {
    suspend fun execute(year: Int, month: Int) =
        repository.getRecordsByMonth(year, month)
}