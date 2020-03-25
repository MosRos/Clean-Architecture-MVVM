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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsListRepositoryImp @Inject constructor(
    val coinsGeckoService: CoinGeckoService,
    val coinsRoomDataSource: CoinsRoomDataSource) : CoinsListRepository {

    private val defaultLimit = 100
    private val defaultOffset = 0

    override fun getCoins(): Flow<Resource<List<Coin>>> {
        return allCoinsList.flow()
    }

    private val allCoinsList: NetworkBoundResource<List<Coin>, List<Coin>, CoinGeckoApiError> by networkBoundResourceLazy(
        initialParams = ::initialCoinsParams,
        dbFetcher = {_, dbLimits, dbOffset -> fetchFromDb()},
        apiFetcher = { fetchFromApi() },
        cacheValidator = {cacheData -> !cacheData.isNullOrEmpty()},
        dataPersister = {coinsList -> saveCoins(coinsList)}
    )

    private val initialCoinsParams = Pair(defaultLimit, defaultOffset)

    private suspend fun fetchFromDb() : List<Coin> {
        return coinsRoomDataSource.getCoinsList()
    }

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> {
        return coinsGeckoService.getCoins()
    }

    suspend fun saveCoins(coins: List<Coin>) {
        coinsRoomDataSource.insertCoins(coins)
    }
}