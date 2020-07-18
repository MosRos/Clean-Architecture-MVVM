/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:54 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:54 PM
 *
 */

package com.morostami.archsample.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.RemoteDataSource
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.BookMarksLocalDataSource
import com.morostami.archsample.data.local.MarketLocalDataSource
import com.morostami.archsample.domain.CryptoMarketRepository
import com.morostami.archsample.domain.base.Resource
import com.morostami.archsample.domain.base.Result
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class CryptoMarketRepositoryImpl @Inject constructor(
    private val marketLocalDataSource: MarketLocalDataSource,
    private val bookMarksLocalDataSource: BookMarksLocalDataSource,
    private val remoteDataSource: RemoteDataSource) : CryptoMarketRepository {

    private val defaultLimit = 100
    private val defaultOffset = 0

    @ExperimentalCoroutinesApi
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

    @ExperimentalCoroutinesApi
    override fun getBookMarks(): Flow<Result<List<RankedCoin>>> {
        return flow {
            emit(Result.Loading)
            val account = bookMarksLocalDataSource.getBookmarkedAccount("guest")
            val bookmarks: List<RankedCoin> = account.bookmarkedCoins
            try {
                emit(Result.Success(bookmarks))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }

    private suspend fun loadFromDB() : List<RankedCoin> {
        val dbResult: List<RankedCoin> = marketLocalDataSource.getAllRankedCoins()
        Timber.e("Market DB Result is ${dbResult.size}")
        return dbResult
    }

    private suspend fun fetchFromNetwork() : NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
        val apiResult = remoteDataSource.getMarketRanks("usd")
        when(apiResult){
            is NetworkResponse.Success -> Timber.e(apiResult.body.size.toString())
            is NetworkResponse.NetworkError -> Timber.e(apiResult.error.toString())
            is NetworkResponse.ServerError -> Timber.e(apiResult.body.toString())
        }
        return apiResult
    }

    private suspend fun saveRanks(rankedCoins: List<RankedCoin>){
        marketLocalDataSource.insertRankedCoins(rankedCoins)
    }
}