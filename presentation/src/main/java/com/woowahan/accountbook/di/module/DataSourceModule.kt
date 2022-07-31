package com.woowahan.accountbook.di.module

import com.woowahan.data.account.AccountLocalDataSourceImpl
import com.woowahan.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideAccountDataSource(repository: AccountRepository): AccountLocalDataSourceImpl {
        return AccountLocalDataSourceImpl(repository)
    }
}