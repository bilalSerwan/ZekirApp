package com.fastlink.zekrapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideZekirCategorySingleton(): ZekirCategorySingleton {
        return ZekirCategorySingleton
    }

    @Provides
    fun provideZekirSingleton(): ZekirSingleton {
        return ZekirSingleton
    }
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}
