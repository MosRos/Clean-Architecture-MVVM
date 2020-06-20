/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:43 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:43 PM
 *
 */

package com.morostami.archsample.data.local

import androidx.annotation.NonNull
import androidx.paging.PagingSource
import androidx.room.*
import com.morostami.archsample.domain.model.RankedCoin

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

    @Query("SELECT * FROM RankedCoin ORDER BY marketCapRank ASC")
    fun getPagedRankedCoins(): PagingSource<Int, RankedCoin>

    @Delete
    suspend fun deleteRankedCoin(rankedCoin: RankedCoin)

    @Delete
    suspend fun deleteRankedCoins(coinsList: List<RankedCoin>)

    @Query("DELETE FROM RankedCoin")
    suspend fun deleteAllRankedCoins()

    @Query("UPDATE RankedCoin SET isBookMarked = :bookmark_it WHERE id = :coinId")
    suspend fun bookmarkCoin(coinId: String, bookmark_it: Boolean = true)

    @Query("SELECT * FROM RankedCoin WHERE isbookmarked = 1")
    suspend fun getBookMarkedList() : List<RankedCoin>
}