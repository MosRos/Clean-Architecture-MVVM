/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 9:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 9:05 PM
 *
 */

package com.morostami.archsample.data.local.doa

import androidx.paging.PagingSource
import androidx.room.*
import com.morostami.archsample.domain.model.Account
import com.morostami.archsample.domain.model.AccountBookmarkCoin
import com.morostami.archsample.domain.model.RankedCoin
import retrofit2.http.DELETE

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createAccount(account: Account)

    @Query("DELETE FROM account WHERE account_id = :accountId")
    suspend fun deleteAccount(accountId: String)

    @Transaction
    @Query("SELECT * FROM account WHERE account_id = :accountId")
    suspend fun getAccountBook(accountId: String): AccountBookmarkCoin

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBookmarkCoins(coinList: List<AccountBookmarkCoin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkCoins(coin: RankedCoin)

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