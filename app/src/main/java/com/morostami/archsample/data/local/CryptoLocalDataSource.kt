/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:48 PM
 *
 */

package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.RankedCoin
import javax.inject.Inject

class CryptoLocalDataSource @Inject constructor(private val cryptoMarketDao: CryptoMarketDao) {
    // RankedCoins
    suspend fun insertRankedCoins(coinsList: List<RankedCoin>){
        cryptoMarketDao.insertRankedCoins(coinsList)
    }

    suspend fun insertRankedCoin(rankedCoin: RankedCoin){
        cryptoMarketDao.insertRankedCoin(rankedCoin)
    }

    suspend fun getAllRankedCoins(): List<RankedCoin>{
        return cryptoMarketDao.getAllRankedCoins()
    }

    suspend fun getRankedCoinsList(): List<RankedCoin>{
        return cryptoMarketDao.getRankedCoinsList()
    }

    suspend fun deleteRankedCoin(rankedCoin: RankedCoin){
        cryptoMarketDao.deleteRankedCoin(rankedCoin)
    }

    suspend fun deleteRankedCoins(coinsList: List<RankedCoin>){
        cryptoMarketDao.deleteRankedCoins(coinsList)
    }
}