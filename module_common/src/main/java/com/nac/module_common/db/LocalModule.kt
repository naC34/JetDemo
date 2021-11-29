package com.nac.module_common.db

import android.content.Context
import com.nac.module_common.db.dao.JetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun providesJetDatabase(@ApplicationContext context: Context): JetDatabase =
        JetDatabase.getInstance(context)

    @Provides
    fun providesJetDao(jetDatabase: JetDatabase): JetDao = jetDatabase.jetDao()

}