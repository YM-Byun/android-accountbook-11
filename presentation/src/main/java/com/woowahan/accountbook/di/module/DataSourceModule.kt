package com.woowahan.accountbook.di.module

import com.woowahan.data.account.AccountLocalDataSource
import com.woowahan.data.account.AccountLocalDataSourceImpl
import com.woowahan.data.entity.DBHelper
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
    fun provideAccountDataSource(dbHelper: DBHelper): AccountLocalDataSource {
        return AccountLocalDataSourceImpl(dbHelper)
    }
}