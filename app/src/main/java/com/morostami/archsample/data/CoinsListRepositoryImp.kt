/*
 * *
 *  * Created by Moslem Rostami on 3/24/20 7:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 7:02 PM
 *
 */

package com.morostami.archsample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.api.RemoteDataSource
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.CoinsLocalDataSource
import com.morostami.archsample.data.local.converters.toCoin
import com.morostami.archsample.data.local.converters.toEntity
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.domain.CoinsListRepository
import com.morostami.archsample.domain.base.Resource
import com.morostami.archsample.domain.model.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CoinsListRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource) : CoinsListRepository {

    companion object {
        const val COINS_PAGE_SIZE = 50
    }

    private val pageConfig = PagingConfig(pageSize = COINS_PAGE_SIZE, prefetchDistance = 15, enablePlaceholders = true, initialLoadSize = 100, maxSize = 200)
    private val coinsPagingSourceFactory = {coinsLocalDataSource.getPagedCoins()}
    private fun getSearchPagingSourceFactory(query: String): () -> PagingSource<Int, CoinEntity> = {coinsLocalDataSource.searchPagedCoins(searchQuery = query)}

    override fun searchCoins(searchQuery: String) : Flow<PagingData<CoinEntity>> {
        val pagingSourceFactory = if (searchQuery.isNullOrEmpty()) coinsPagingSourceFactory else getSearchPagingSourceFactory(searchQuery)

        return Pager(
            config = pageConfig,
            initialKey = 1,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @ExperimentalCoroutinesApi
    override fun getCoins(): Flow<Resource<List<Coin>>> {
        return  object : NetworkBoundResource<List<Coin>, List<Coin>, CoinGeckoApiError>(){
            override suspend fun getFromDatabase(
                isRefreshed: Boolean,
                limit: Int,
                offset: Int
            ): List<Coin>? {
                return fetchFromDb()
            }

            override suspend fun validateCache(cachedData: List<Coin>?): Boolean {
                return !cachedData.isNullOrEmpty()
            }

            override suspend fun getFromApi(): NetworkResponse<List<Coin>, CoinGeckoApiError> {
                return fetchFromApi()
            }

            override suspend fun persistData(apiData: List<Coin>) {
                saveCoins(apiData)
            }
        }.flow()

    }

//    override fun searchCoins(searchInput: String): Flow<Resource<List<Coin>>> {
//        return flow() {
//            val results: List<Coin> = localSearchCoins(searchInput)
//            Timber.e("search results size ${results.size}")
//            emit(Resource.Success(localSearchCoins(searchInput)))
//        }
//    }

    private suspend fun localSearchCoins(input: String) : List<Coin> {
        return GlobalScope.async(Dispatchers.IO){
            coinsLocalDataSource.searchCoins(input).mapNotNull { coinEntity -> coinEntity.toCoin() }
        }.await()
    }

    private suspend fun fetchFromDb() : List<Coin> {
//        val coinsResult: List<Coin> = GlobalScope.async(Dispatchers.IO) {
//            coinsRoomDataSource.getCoinsList()
//        }.await()
        val coinsResult: List<Coin> = coinsLocalDataSource.getCoinsList().mapNotNull { coinEntity -> coinEntity.toCoin() }
        Timber.e(coinsResult.size.toString())
        return coinsResult
    }

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> {
        val coinsResult = remoteDataSource.getCoins()
        when(coinsResult){
            is NetworkResponse.Success -> Timber.e(coinsResult.body.size.toString())
            is NetworkResponse.NetworkError -> Timber.e(coinsResult.error.toString())
            is NetworkResponse.ServerError -> Timber.e(coinsResult.body.toString())
        }
        return coinsResult
    }

    suspend fun saveCoins(coins: List<Coin>) {
        Timber.e("Coins To Insert In DB ${coins.size}")
//        GlobalScope.async(Dispatchers.IO) {
//            coinsRoomDataSource.insertCoins(coins)
//        }
        coinsLocalDataSource.insertCoins(coins.mapNotNull { coin -> coin.toEntity() })
    }
}