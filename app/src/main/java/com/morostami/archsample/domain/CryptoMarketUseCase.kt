/*
 * *
 *  * Created by Moslem Rostami on 4/9/20 9:20 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/9/20 9:20 PM
 *
 */

package com.morostami.archsample.domain

import com.morostami.archsample.domain.base.Resource
import com.morostami.archsample.domain.model.RankedCoin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoMarketUseCase @Inject constructor(private val cryptoMarketRepository: CryptoMarketRepository) {

    fun getMarketRanks() : Flow<Resource<List<RankedCoin>>> = cryptoMarketRepository.getRanks()

    fun getBookMarks() : Flow<Resource<List<RankedCoin>>> = cryptoMarketRepository.getBookMarks()
}