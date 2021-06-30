/*
 * *
 *  * Created by Moslem Rostami on 6/17/20 5:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/17/20 5:04 PM
 *
 */

package com.morostami.archsample.data

import androidx.paging.*
import com.morostami.archsample.data.local.MarketLocalDataSource
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import com.morostami.archsample.domain.MarketRanksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class MarketRanksRepositoryImpl @Inject constructor(
    private val marketLocalDataSource: MarketLocalDataSource,
    private val marketRanksMediator: MarketRanksMediator) : MarketRanksRepository {

    override fun getRanks(): Flow<PagingData<RankedCoinEntity>> {

        val pagingSourceFactory = { marketLocalDataSource.getPagedRankedCoins() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 5, enablePlaceholders = true, initialLoadSize = 100, maxSize = 200),
            remoteMediator = marketRanksMediator,
            initialKey = 1,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}