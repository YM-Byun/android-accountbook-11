package com.woowahan.accountbook.di.module

import com.woowahan.domain.accountUseCase.AddIncomeCategoryUseCase
import com.woowahan.domain.accountUseCase.AddPaymentUseCase
import com.woowahan.domain.accountUseCase.AddSpendingUseCase
import com.woowahan.domain.accountUseCase.GetIncomeCategoryUseCase
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
    fun provideGetIncomeCategory(repository: AccountRepository): GetIncomeCategoryUseCase {
        return GetIncomeCategoryUseCase(repository)
    }
}