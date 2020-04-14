/*
 * *
 *  * Created by Moslem Rostami on 3/26/20 3:31 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 3:31 AM
 *
 */

package com.morostami.archsample.domain

import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsListUseCase @Inject constructor(private val coinsListRepository: CoinsListRepository) {
    fun getCoinsList(): Flow<Resource<List<Coin>>> {
        return coinsListRepository.getCoins()
    }

    fun searchCoins(inputQuery: String) : Flow<Resource<List<Coin>>> {
        return coinsListRepository.searchCoins(inputQuery)
    }
}