package com.woowahan.accountbook.di.module

import android.content.Context
import com.woowahan.data.entity.DBHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBHelperModule {

    @Provides
    @Singleton
    fun provideDBHelper(
        @ApplicationContext appContext: Context
    ): DBHelper {
        return DBHelper(appContext, "account.db", null, 1)
    }
}