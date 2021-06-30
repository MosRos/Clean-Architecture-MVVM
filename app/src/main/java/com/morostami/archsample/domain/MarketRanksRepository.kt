/*
 * *
 *  * Created by Moslem Rostami on 6/17/20 5:07 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/17/20 5:07 PM
 *
 */

package com.morostami.archsample.domain

import androidx.paging.PagingData
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import kotlinx.coroutines.flow.Flow

interface MarketRanksRepository {
    fun getRanks() : Flow<PagingData<RankedCoinEntity>>
}