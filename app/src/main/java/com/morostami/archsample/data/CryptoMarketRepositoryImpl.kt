/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:54 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:54 PM
 *
 */

package com.morostami.archsample.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.CryptoLocalDataSource
import com.morostami.archsample.domain.CryptoMarketRepository
import com.morostami.archsample.domain.model.RankedCoin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CryptoMarketRepositoryImpl @Inject constructor(
    private val cryptoLocalDataSource: CryptoLocalDataSource,
    private val coinGeckoService: CoinGeckoService) : CryptoMarketRepository {

    private val defaultLimit = 100
    private val defaultOffset = 0

    override fun getRanks(): Flow<Resource<List<RankedCoin>>> {
        return object : NetworkBoundResource<List<RankedCoin>, List<RankedCoin>, CoinGeckoApiError>() {

            init {
                updateParams(defaultLimit, defaultOffset)
            }

            override suspend fun getFromDatabase(
                isRefreshed: Boolean,
                limit: Int,
                offset: Int
            ): List<RankedCoin>? {
                return loadFromDB()
            }

            override suspend fun validateCache(cachedData: List<RankedCoin>?): Boolean {
                return !cachedData.isNullOrEmpty()
            }

            override suspend fun getFromApi(): NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
                return fetchFromNetwork()
            }

            override suspend fun persistData(apiData: List<RankedCoin>) {
                saveRanks(apiData)
            }
        }.flow()
    }

    override fun getBookMarks(): Flow<Resource<List<RankedCoin>>> {
        return object : NetworkBoundResource<List<RankedCoin>, List<RankedCoin>, CoinGeckoApiError>(){
            var bookmarkedIds: ArrayList<String> = ArrayList()

            override suspend fun getFromDatabase(
                isRefreshed: Boolean,
                limit: Int,
                offset: Int
            ): List<RankedCoin>? {
                val bookmarks: List<RankedCoin>? = loadBookMarksFromDB()
                bookmarks?.apply {
                    for (bookmark in bookmarks){
                        bookmarkedIds.add(bookmark.id)
                    }
                }
                return bookmarks
            }

            override suspend fun validateCache(cachedData: List<RankedCoin>?): Boolean {
                return !cachedData.isNullOrEmpty()
            }

            override suspend fun getFromApi(): NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
                return fetchBookMarksFromNetwork(bookmarkedIds)
            }

            override suspend fun persistData(apiData: List<RankedCoin>) {
                saveRanks(apiData)
            }
        }.flow()
    }

    private suspend fun loadFromDB() : List<RankedCoin> {
        val dbResult: List<RankedCoin> = cryptoLocalDataSource.getAllRankedCoins()
        Timber.e("Market DB Result is ${dbResult.size}")
        return dbResult
    }

    private suspend fun loadBookMarksFromDB() : List<RankedCoin> {
        val dbResult: List<RankedCoin> = cryptoLocalDataSource.getBookMarkedList()
        Timber.e("Market DB Result is ${dbResult.size}")
        return dbResult
    }

    private suspend fun fetchFromNetwork() : NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
        val apiResult = coinGeckoService.getMarketRanks("usd")
        when(apiResult){
            is NetworkResponse.Success -> Timber.e(apiResult.body.size.toString())
            is NetworkResponse.NetworkError -> Timber.e(apiResult.error.toString())
            is NetworkResponse.ServerError -> Timber.e(apiResult.body.toString())
        }
        return apiResult
    }

    private suspend fun fetchBookMarksFromNetwork(ids: List<String>) : NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
        val apiResult = coinGeckoService.getBookMarks("usd", ids)
        when(apiResult){
            is NetworkResponse.Success -> Timber.e(apiResult.body.size.toString())
            is NetworkResponse.NetworkError -> Timber.e(apiResult.error.toString())
            is NetworkResponse.ServerError -> Timber.e(apiResult.body.toString())
        }
        return apiResult
    }

    private suspend fun saveRanks(rankedCoins: List<RankedCoin>){
        cryptoLocalDataSource.insertRankedCoins(rankedCoins)
    }
}