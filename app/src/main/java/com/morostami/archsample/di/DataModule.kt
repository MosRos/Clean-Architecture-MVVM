/*
 * *
 *  * Created by Moslem Rostami on 3/25/20 4:19 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/25/20 4:19 PM
 *
 */

package com.morostami.archsample.di

import android.app.Application
import androidx.room.Room
import com.morostami.archsample.data.CoinsListRepositoryImpl
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.local.CoinsRoomDao
import com.morostami.archsample.data.local.CoinsRoomDataBase
import com.morostami.archsample.data.local.CoinsRoomDataSource
import com.morostami.archsample.domain.CoinsListRepository
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideCoinsRoomDb(application: Application) : CoinsRoomDataBase {
        return Room.databaseBuilder(application.applicationContext, CoinsRoomDataBase::class.java, "coins_db")
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinsRoomDao(coinsRoomDataBase: CoinsRoomDataBase) : CoinsRoomDao {
        return coinsRoomDataBase.coindDao()
    }

    @Singleton
    @Provides
    fun provideCryptocurrencyConfig() : RealmConfiguration {
        return RealmConfiguration.Builder()
            .name("cryptocurrency.realm")
            .schemaVersion(1)
//        .addModule(CryptoCurrencyModule())
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinsListRepository(coinGeckoService: CoinGeckoService,  coinsRoomDataSource: CoinsRoomDataSource) : CoinsListRepository {
        return CoinsListRepositoryImpl(coinGeckoService, coinsRoomDataSource)
    }
}