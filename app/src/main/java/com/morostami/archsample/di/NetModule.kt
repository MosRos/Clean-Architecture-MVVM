/*
 * *
 *  * Created by Moslem Rostami on 3/22/20 6:26 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/22/20 6:26 PM
 *
 */

package com.morostami.archsample.di

import android.content.Context
import androidx.annotation.NonNull
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.morostami.archsample.BuildConfig
import com.morostami.archsample.data.api.CoinGeckoService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {
    @Singleton
    @Provides
    fun okHttpCache(context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1000 * 1000)
    }

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(@NonNull loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("httoClientCache")
    fun provideHttpClientWithCache(@NonNull loggingInterceptor: HttpLoggingInterceptor, cache: Cache) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@NonNull httoClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(httoClient)
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinGeckoService(@NonNull retrofit: Retrofit) : CoinGeckoService {
        return retrofit.create(CoinGeckoService::class.java)
    }
}