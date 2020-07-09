/*
 * *
 *  * Created by Moslem Rostami on 4/14/20 9:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/14/20 9:21 AM
 *
 */

package com.morostami.archsample.domain

import androidx.paging.PagingData
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinsListRepository {
    fun getCoins() : Flow<Resource<List<Coin>>>
    fun searchCoins(searchInput: String) : Flow<PagingData<Coin>>
}