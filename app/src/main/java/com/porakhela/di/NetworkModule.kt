package com.porakhela.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module for network-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    // Add network dependencies here when needed
    // Example:
    // @Provides
    // @Singleton
    // fun provideOkHttpClient(): OkHttpClient { ... }
    // 
    // @Provides
    // @Singleton
    // fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit { ... }
}