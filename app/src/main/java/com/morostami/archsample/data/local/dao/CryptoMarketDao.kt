/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 9:39 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 4:30 PM
 *
 */

package com.morostami.archsample.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.morostami.archsample.data.local.entities.RankedCoinEntity

@Dao
interface CryptoMarketDao {

    // For RankedCoins
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRankedCoins(coinsList: List<RankedCoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRankedCoin(rankedCoin: RankedCoinEntity)

    @Query("SELECT * FROM RankedCoin ORDER BY marketCapRank ASC")
    suspend fun getAllRankedCoins(): List<RankedCoinEntity>

    @Query("SELECT * FROM RankedCoin")
    suspend fun getRankedCoinsList(): List<RankedCoinEntity>

    @Query("SELECT * FROM RankedCoin ORDER BY marketCapRank ASC")
    fun getPagedRankedCoins(): PagingSource<Int, RankedCoinEntity>

    @Delete
    suspend fun deleteRankedCoin(rankedCoin: RankedCoinEntity)

    @Delete
    suspend fun deleteRankedCoins(coinsList: List<RankedCoinEntity>)

    @Query("DELETE FROM RankedCoin")
    suspend fun deleteAllRankedCoins()
}