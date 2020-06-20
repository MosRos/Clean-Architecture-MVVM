/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:43 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:43 PM
 *
 */

package com.morostami.archsample.data.local

import androidx.room.*
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoMarketDao {

    // For RankedCoins
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRankedCoins(coinsList: List<RankedCoin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRankedCoin(rankedCoin: RankedCoin)

    @Query("SELECT * FROM RankedCoin")
    suspend fun getAllRankedCoins(): List<RankedCoin>

    @Query("SELECT * FROM RankedCoin")
    suspend fun getRankedCoinsList(): List<RankedCoin>

    @Delete
    suspend fun deleteRankedCoin(rankedCoin: RankedCoin)

    @Delete
    suspend fun deleteRankedCoins(coinsList: List<RankedCoin>)
}