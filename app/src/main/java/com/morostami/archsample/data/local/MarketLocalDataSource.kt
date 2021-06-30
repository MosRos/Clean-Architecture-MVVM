/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:48 PM
 *
 */

package com.morostami.archsample.data.local

import androidx.paging.PagingSource
import com.morostami.archsample.data.local.converters.toEntity
import com.morostami.archsample.data.local.converters.toRankedCoin
import com.morostami.archsample.data.local.entities.CoinsRemoteKeys
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import com.morostami.archsample.model.RankedCoin
import javax.inject.Inject

class MarketLocalDataSource @Inject constructor(
    private val cryptoDataBase: CryptoDataBase) {

    private val cryptoMarketDao by lazy { cryptoDataBase.cryptoMarketDao() }
    private val remoteKeysDao by lazy { cryptoDataBase.remoteKeysDao() }

    // RankedCoins
    suspend fun insertRankedCoins(coinsList: List<RankedCoinEntity>) = cryptoMarketDao.insertRankedCoins(coinsList)

    suspend fun insertRankedCoin(rankedCoin: RankedCoin) = cryptoMarketDao.insertRankedCoin(rankedCoin.toEntity())

    suspend fun getAllRankedCoins(): List<RankedCoin> = cryptoMarketDao.getAllRankedCoins().map { rankedCoinEntity -> rankedCoinEntity.toRankedCoin() }

    suspend fun getRankedCoinsList(): List<RankedCoin> = cryptoMarketDao.getRankedCoinsList().map { rankedCoinEntity -> rankedCoinEntity.toRankedCoin() }

    fun getPagedRankedCoins(): PagingSource<Int, RankedCoinEntity> = cryptoMarketDao.getPagedRankedCoins()

    suspend fun deleteRankedCoin(rankedCoin: RankedCoinEntity) = cryptoMarketDao.deleteRankedCoin(rankedCoin)

    suspend fun deleteRankedCoins(coinsList: List<RankedCoinEntity>) = cryptoMarketDao.deleteRankedCoins(coinsList)

    suspend fun deleteAllRankedCoins() = cryptoMarketDao.deleteAllRankedCoins()

    suspend fun insertAllCoinsRemoteKeys(remoteKeys: List<CoinsRemoteKeys>) = remoteKeysDao.insertAllRemoteKeys(remoteKeys)

    suspend fun getRemoteKeysCoinId(coinId: String): CoinsRemoteKeys? = remoteKeysDao.remoteKeysCoinId(coinId)

    suspend fun clearCoinsRemoteKeys() = remoteKeysDao.clearCoinsRemoteKeys()
}