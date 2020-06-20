/*
 * *
 *  * Created by Moslem Rostami on 6/17/20 5:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/17/20 5:04 PM
 *
 */

package com.morostami.archsample.data

import androidx.paging.*
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.local.CryptoLocalDataSource
import com.morostami.archsample.domain.MarketRanksRepository
import com.morostami.archsample.domain.model.RankedCoin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class MarketRanksRepositoryImpl @Inject constructor(
    private val cryptoLocalDataSource: CryptoLocalDataSource,
    private val coinGeckoService: CoinGeckoService,
    private val marketRanksMediator: MarketRanksMediator) : MarketRanksRepository {

    override suspend fun getRanks(): Flow<PagingData<RankedCoin>> {

        val pagingSourceFactory = { cryptoLocalDataSource.getPagedRankedCoins() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 15),
            remoteMediator = marketRanksMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}