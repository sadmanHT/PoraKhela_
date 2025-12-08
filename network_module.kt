package com.porakhela.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.porakhela.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Network Module for Applink API
 * Provides Retrofit, OkHttpClient, and API services
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    /**
     * Provides JSON configuration for serialization
     */
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
    }
    
    /**
     * Provides OkHttpClient for Mock API (development/testing)
     */
    @Provides
    @Singleton
    @MockApiClient
    fun provideMockOkHttpClient(
        mockApplinkInterceptor: MockApplinkInterceptor,
        loggingInterceptor: ApplinkLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockApplinkInterceptor) // Mock responses
            .addInterceptor(loggingInterceptor)
            .connectTimeout(ApplinkConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApplinkConfig.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApplinkConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
    
    /**
     * Provides OkHttpClient for Real API (production)
     */
    @Provides
    @Singleton
    @RealApiClient
    fun provideRealOkHttpClient(
        authInterceptor: ApplinkAuthInterceptor,
        loggingInterceptor: ApplinkLoggingInterceptor,
        errorInterceptor: ApplinkErrorInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // Add authentication headers
            .addInterceptor(errorInterceptor) // Handle errors and retries
            .addInterceptor(loggingInterceptor) // Log requests/responses
            .connectTimeout(ApplinkConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApplinkConfig.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApplinkConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
    
    /**
     * Provides Retrofit instance for Mock API
     */
    @Provides
    @Singleton
    @MockApiRetrofit
    fun provideMockRetrofit(
        @MockApiClient okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://mock.porakhela.com/") // Mock base URL
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
    
    /**
     * Provides Retrofit instance for Real API
     */
    @Provides
    @Singleton
    @RealApiRetrofit
    fun provideRealRetrofit(
        @RealApiClient okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(ApplinkConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
    
    /**
     * Provides Mock Applink API Service
     */
    @Provides
    @Singleton
    fun provideMockApplinkApiService(
        @MockApiRetrofit retrofit: Retrofit
    ): ApplinkApiService {
        return retrofit.create(ApplinkApiService::class.java)
    }
    
    /**
     * Provides Real Applink API Service
     */
    @Provides
    @Singleton
    fun provideRealApplinkApiService(
        @RealApiRetrofit retrofit: Retrofit
    ): ApplinkRealApiService {
        return retrofit.create(ApplinkRealApiService::class.java)
    }
    
    /**
     * Provides Mock Applink Interceptor
     */
    @Provides
    @Singleton
    fun provideMockApplinkInterceptor(): MockApplinkInterceptor {
        return MockApplinkInterceptor()
    }
    
    /**
     * Provides Auth Interceptor
     */
    @Provides
    @Singleton
    fun provideApplinkAuthInterceptor(): ApplinkAuthInterceptor {
        return ApplinkAuthInterceptor()
    }
    
    /**
     * Provides Logging Interceptor
     */
    @Provides
    @Singleton
    fun provideApplinkLoggingInterceptor(): ApplinkLoggingInterceptor {
        return ApplinkLoggingInterceptor()
    }
    
    /**
     * Provides Error Interceptor
     */
    @Provides
    @Singleton
    fun provideApplinkErrorInterceptor(): ApplinkErrorInterceptor {
        return ApplinkErrorInterceptor()
    }
}

/**
 * Qualifiers to distinguish between Mock and Real API clients
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockApiClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealApiClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockApiRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealApiRetrofit
