/*
 * *
 *  * Created by Moslem Rostami on 7/16/20 12:55 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/16/20 12:55 AM
 *
 */

package com.morostami.archsample.data.local

import com.morostami.archsample.data.local.doa.BookmarksDao
import com.morostami.archsample.domain.model.Account
import com.morostami.archsample.domain.model.AccountBookmarkCoin
import com.morostami.archsample.domain.model.RankedCoin
import javax.inject.Inject

class BookMarksLocalDataSource @Inject constructor(private val cryptoDataBase: CryptoDataBase) : BookmarksDao {

    private val bookmarksDao by lazy { cryptoDataBase.bookmarksDao() }

    override suspend fun createAccount(account: Account) = bookmarksDao.createAccount(account)

    override suspend fun deleteAccount(accountId: String) = bookmarksDao.deleteAccount(accountId)

    override suspend fun getBookmarkedAccount(accountId: String): AccountBookmarkCoin = bookmarksDao.getBookmarkedAccount(accountId)

    override suspend fun getAllBookmarkedCoins(accountId: String): List<AccountBookmarkCoin> = bookmarksDao.getAllBookmarkedCoins(accountId)

    override suspend fun deleteAllBookmarks(accountId: String) = bookmarksDao.deleteAllBookmarks(accountId)

    override suspend fun getAccountBook(accountId: String): AccountBookmarkCoin = bookmarksDao.getAccountBook(accountId)

    override suspend fun insertBookmarkCoins(coin: RankedCoin) = bookmarksDao.insertBookmarkCoins(coin)

    suspend fun addBookmark(account: Account, rankedCoin: RankedCoin) {
        val resultAccount = getAccountBook(accountId = account.accountId)
        val bookmarkedCoin = rankedCoin
        bookmarkedCoin.accountBookId = resultAccount.account.accountId
        insertBookmarkCoins(bookmarkedCoin)
    }

    suspend fun deleteBookmark(account: Account, rankedCoin: RankedCoin) {
        val resultAccount = getAccountBook(accountId = account.accountId)
        resultAccount.bookmarkedCoins.forEach { coin ->
            if (coin.id == rankedCoin.id){
                coin.accountBookId = ""
            }
        }
    }
}