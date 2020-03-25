/*
 * *
 *  * Created by Moslem Rostami on 3/24/20 12:23 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 12:23 PM
 *
 */

package com.morostami.archsample.domain

import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinsListRepository {
    fun getCoins() : Flow<Resource<List<Coin>>>
}