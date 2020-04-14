/*
 * *
 *  * Created by Moslem Rostami on 3/24/20 7:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 7:02 PM
 *
 */

package com.morostami.archsample.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.CoinsRoomDataSource
import com.morostami.archsample.domain.CoinsListRepository
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class CoinsListRepositoryImpl @Inject constructor(
    private val coinsGeckoService: CoinGeckoService,
    private val coinsRoomDataSource: CoinsRoomDataSource) : CoinsListRepository {

    private val defaultLimit = 100
    private val defaultOffset = 0

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

    override fun searchCoins(searchInput: String): Flow<Resource<List<Coin>>> {
        return flow() {
            val results: List<Coin> = localSearchCoins(searchInput)
            Timber.e("search results size ${results.size}")
            emit(Resource.Success(localSearchCoins(searchInput)))
        }
    }

    private suspend fun localSearchCoins(input: String) : List<Coin> {
        return GlobalScope.async(Dispatchers.IO){
            coinsRoomDataSource.searchCoins(input)
        }.await()
    }

    private suspend fun fetchFromDb() : List<Coin> {
//        val coinsResult: List<Coin> = GlobalScope.async(Dispatchers.IO) {
//            coinsRoomDataSource.getCoinsList()
//        }.await()
        val coinsResult: List<Coin> = coinsRoomDataSource.getCoinsList()
        Timber.e(coinsResult.size.toString())
        return coinsResult
    }

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> {
        val coinsResult = coinsGeckoService.getCoins()
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
        coinsRoomDataSource.insertCoins(coins)
    }
}