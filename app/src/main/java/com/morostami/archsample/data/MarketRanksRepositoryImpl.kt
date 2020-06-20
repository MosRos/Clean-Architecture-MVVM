/*
 * *
 *  * Created by Moslem Rostami on 6/17/20 5:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/17/20 5:04 PM
 *
 */

package com.morostami.archsample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.local.CryptoLocalDataSource
import com.morostami.archsample.domain.MarketRanksRepository
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class MarketRanksRepositoryImpl @Inject constructor(
    private val cryptoLocalDataSource: CryptoLocalDataSource,
    private val coinGeckoService: CoinGeckoService,
    private val marketRanksMediator: MarketRanksMediator) : MarketRanksRepository {

    override fun getRanks(): Flow<PagingData<RankedCoin>> {

        val pagingSourceFactory = { cryptoLocalDataSource.getPagedRankedCoins() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 15, enablePlaceholders = true, initialLoadSize = 100, maxSize = 200),
            remoteMediator = marketRanksMediator,
            initialKey = 1,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}