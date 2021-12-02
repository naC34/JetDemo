package com.nac.module_main.request

import com.nac.lib_net.NetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JetApiInject {

    @Singleton
    @Provides
    fun provideNetService(): JetApi = NetManager.create()
}