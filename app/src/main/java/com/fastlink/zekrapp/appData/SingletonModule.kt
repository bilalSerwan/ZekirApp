package com.fastlink.zekrapp.appData

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideZekirCategorySingleton(): ZekirCategorySingleton {
        return ZekirCategorySingleton
    }

    @Provides
    @Singleton
    fun provideZekirSingleton(): ZekirSingleton {
        return ZekirSingleton
    }
}
