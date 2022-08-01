package com.woowahan.accountbook.di.module

import com.woowahan.domain.accountUseCase.*
import com.woowahan.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideAddPaymentUseCase(repository: AccountRepository): AddPaymentUseCase {
        return AddPaymentUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddIncomeUseCase(repository: AccountRepository): AddIncomeCategoryUseCase {
        return AddIncomeCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddSpendingUseCase(repository: AccountRepository): AddSpendingUseCase {
        return AddSpendingUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetIncomeCategoryUseCase(repository: AccountRepository): GetIncomeCategoryUseCase {
        return GetIncomeCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSpendingCategoryUseCase(repository: AccountRepository): GetSpendingCategoryUseCase {
        return GetSpendingCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPaymentsUseCase(repository: AccountRepository): GetPaymentsUseCase {
        return GetPaymentsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddIncomeRecordUseCase(repository: AccountRepository): AddIncomeRecordUseCase {
        return AddIncomeRecordUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddSpendingRecordUseCase(repository: AccountRepository): AddSpendingRecordUseCase {
        return AddSpendingRecordUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRecordsByMonthUseCase(repository: AccountRepository): GetRecordsByMonthUseCase {
        return GetRecordsByMonthUseCase(repository)
    }
}