package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.Coin
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception
/**
 * Created by Moslem Rostami on $today
 * Copyright (c) $today.year . All rights reserved.
 * Last modified $file.lastModified
 */

@Singleton
class CoinsRealmDataSource @Inject constructor(coinConfig: RealmConfiguration) : CoinsCRUD {

    private val coinsRealm: Realm = Realm.getInstance(coinConfig)

    override suspend fun insertCoins(coinsList: List<Coin>) {
        coinsRealm.use {
            try {
                coinsRealm.executeTransactionAsync({coinsRealm ->
                    coinsRealm.insertOrUpdate(coinsList)
                },{
                    // Transaction was a success
                }, {error ->
                    // Transaction failed
                })
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override suspend fun insertCoin(coin: Coin) {
        coinsRealm.use {
            try {
                coinsRealm.executeTransaction {coinsRealm ->
                    coinsRealm.insertOrUpdate(coin)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override suspend fun getAllCoins(): List<Coin> {
        coinsRealm.use {
            return coinsRealm.where(Coin::class.java).findAllAsync()
        }
    }

    override suspend fun getCoinsList(): List<Coin> {
        return coinsRealm.where(Coin::class.java)
            .limit(100)
            .findAllAsync()
    }

    override suspend fun deletCoin(coin: Coin) {
        coinsRealm.use {
            coinsRealm.executeTransaction{coinsRealm ->
                try {
                    coin.deleteFromRealm()
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

    override suspend fun deletCoins(coinsList: List<Coin>) {
        coinsRealm.executeTransaction {
            for (coin in coinsList) {
                try {
                    coin.deleteFromRealm()
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

}