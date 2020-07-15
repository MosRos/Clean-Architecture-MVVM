/*
 * *
 *  * Created by Moslem Rostami on 3/25/20 4:19 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/25/20 4:19 PM
 *
 */

package com.morostami.archsample.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.morostami.archsample.data.CoinsListRepositoryImpl
import com.morostami.archsample.data.CryptoMarketRepositoryImpl
import com.morostami.archsample.data.MarketRanksMediator
import com.morostami.archsample.data.MarketRanksRepositoryImpl
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.api.RemoteDataSource
import com.morostami.archsample.data.local.*
import com.morostami.archsample.domain.CoinsListRepository
import com.morostami.archsample.domain.CryptoMarketRepository
import com.morostami.archsample.domain.MarketRanksRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideCoinsRoomDb(application: Application) : CryptoDataBase {
        return Room.databaseBuilder(application.applicationContext, CryptoDataBase::class.java, "coins_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinsListRepository(remoteDataSource: RemoteDataSource, coinsLocalDataSource: CoinsLocalDataSource) : CoinsListRepository {
        return CoinsListRepositoryImpl(remoteDataSource, coinsLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideCryptoMarketRepository(marketLocalDataSource: MarketLocalDataSource, remoteDataSource: RemoteDataSource) : CryptoMarketRepository {
        return CryptoMarketRepositoryImpl(marketLocalDataSource, remoteDataSource)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideMarketRanksRepository(marketLocalDataSource: MarketLocalDataSource,
                                     marketRanksMediator: MarketRanksMediator
    ) : MarketRanksRepository {
        return MarketRanksRepositoryImpl(marketLocalDataSource, marketRanksMediator)
    }
}