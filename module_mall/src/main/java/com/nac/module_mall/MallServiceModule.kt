package com.nac.module_mall

import com.nac.export_mall.IMallService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MallServiceModule {

    @Binds
    abstract fun bindsMallService(service: MallService): IMallService
}