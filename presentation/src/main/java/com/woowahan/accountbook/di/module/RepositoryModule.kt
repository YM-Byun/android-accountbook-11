package com.woowahan.accountbook.di.module

import com.woowahan.data.account.AccountLocalDataSource
import com.woowahan.data.account.AccountRepositoryImpl
import com.woowahan.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAccountRepository(accountLocalDataSource: AccountLocalDataSource): AccountRepository =
        AccountRepositoryImpl(accountLocalDataSource)
}