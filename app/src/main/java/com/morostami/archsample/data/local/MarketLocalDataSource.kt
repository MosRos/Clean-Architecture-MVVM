/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:48 PM
 *
 */

package com.morostami.archsample.data.local

import androidx.paging.PagingSource
import com.morostami.archsample.domain.model.CoinsRemoteKeys
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketLocalDataSource @Inject constructor(
    private val cryptoDataBase: CryptoDataBase) {

    private val cryptoMarketDao by lazy { cryptoDataBase.cryptoMarketDao() }
    private val remoteKeysDao by lazy { cryptoDataBase.remoteKeysDao() }

    // RankedCoins
    suspend fun insertRankedCoins(coinsList: List<RankedCoin>) = cryptoMarketDao.insertRankedCoins(coinsList)

    suspend fun insertRankedCoin(rankedCoin: RankedCoin) = cryptoMarketDao.insertRankedCoin(rankedCoin)

    suspend fun getAllRankedCoins(): List<RankedCoin> = cryptoMarketDao.getAllRankedCoins()

    suspend fun getRankedCoinsList(): List<RankedCoin> = cryptoMarketDao.getRankedCoinsList()

    fun getPagedRankedCoins(): PagingSource<Int, RankedCoin> = cryptoMarketDao.getPagedRankedCoins()

    suspend fun deleteRankedCoin(rankedCoin: RankedCoin) = cryptoMarketDao.deleteRankedCoin(rankedCoin)

    suspend fun deleteRankedCoins(coinsList: List<RankedCoin>) = cryptoMarketDao.deleteRankedCoins(coinsList)

    suspend fun deleteAllRankedCoins() = cryptoMarketDao.deleteAllRankedCoins()

    suspend fun bookmarkCoin(rankedCoin: RankedCoin) = cryptoMarketDao.bookmarkCoin(rankedCoin.id)

    suspend fun bookmarkCoin(coinId: String) = cryptoMarketDao.bookmarkCoin(coinId)

    suspend fun getBookMarkedList() : List<RankedCoin> = cryptoMarketDao.getBookMarkedList()

    suspend fun insertAllCoinsRemoteKeys(remoteKeys: List<CoinsRemoteKeys>) = remoteKeysDao.insertAllRemoteKeys(remoteKeys)

    suspend fun getRemoteKeysCoinId(coinId: String): CoinsRemoteKeys? = remoteKeysDao.remoteKeysCoinId(coinId)

    suspend fun clearCoinsRemoteKeys() = remoteKeysDao.clearCoinsRemoteKeys()
}