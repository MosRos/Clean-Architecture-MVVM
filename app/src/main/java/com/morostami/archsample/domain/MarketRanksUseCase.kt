/*
 * *
 *  * Created by Moslem Rostami on 6/17/20 5:09 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/17/20 5:09 PM
 *
 */

package com.morostami.archsample.domain

import androidx.paging.PagingData
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketRanksUseCase @Inject constructor(private val marketRanksRepository: MarketRanksRepository) {
    fun getPagedRanks() : Flow<PagingData<RankedCoinEntity>> = marketRanksRepository.getRanks()
}