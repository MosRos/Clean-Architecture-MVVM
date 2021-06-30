/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 9:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 9:05 PM
 *
 */

package com.morostami.archsample.data.local.dao

import androidx.room.*
import com.morostami.archsample.data.local.entities.AccountBookmarkCoin
import com.morostami.archsample.data.local.entities.AccountEntity
import com.morostami.archsample.data.local.entities.RankedCoinEntity

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createAccount(accountEntity: AccountEntity)

    @Query("DELETE FROM Account WHERE account_id = :accountId")
    suspend fun deleteAccount(accountId: String)

    @Transaction
    @Query("SELECT * FROM Account WHERE account_id = :accountId")
    suspend fun getAccountBook(accountId: String): AccountBookmarkCoin

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBookmarkCoins(coinList: List<AccountBookmarkCoin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkCoins(coin: RankedCoinEntity)

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBookmarkCoin(accountBookmarkCoin: AccountBookmarkCoin)

    @Transaction
    @Query("SELECT * FROM Account WHERE account_id = :accountId")
    suspend fun getBookmarkedAccount(accountId: String): AccountBookmarkCoin

    @Transaction
    @Query("SELECT * FROM Account WHERE account_id = :accountId")
    suspend fun getAllBookmarkedCoins(accountId: String): List<AccountBookmarkCoin>

    @Query("DELETE FROM Account WHERE account_id = :accountId")
    suspend fun deleteAllBookmarks(accountId: String)
}