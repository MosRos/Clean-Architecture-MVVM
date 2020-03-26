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

            override fun flow(): Flow<Resource<List<Coin>>> {
                return super.flow()
            }
        }.flow()

    }

    private suspend fun fetchFromDb() : List<Coin> {
        return GlobalScope.async(Dispatchers.IO) {
            coinsRoomDataSource.getCoinsList()
        }.await()
    }

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> {
        return coinsGeckoService.getCoins()
    }

    suspend fun saveCoins(coins: List<Coin>) {
        GlobalScope.async(Dispatchers.IO) {
            coinsRoomDataSource.insertCoins(coins)
        }
    }
}