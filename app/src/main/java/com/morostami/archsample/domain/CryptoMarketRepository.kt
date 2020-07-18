/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 8:53 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 8:53 PM
 *
 */

package com.morostami.archsample.domain

import com.morostami.archsample.domain.base.Resource
import com.morostami.archsample.domain.base.Result
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.flow.Flow

interface CryptoMarketRepository {
    fun getRanks() : Flow<Resource<List<RankedCoin>>>
    fun getBookMarks() : Flow<Result<List<RankedCoin>>>
}