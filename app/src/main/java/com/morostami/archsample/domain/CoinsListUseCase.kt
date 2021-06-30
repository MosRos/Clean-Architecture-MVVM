/*
 * *
 *  * Created by Moslem Rostami on 3/26/20 3:31 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 3:31 AM
 *
 */

package com.morostami.archsample.domain

import androidx.paging.PagingData
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.domain.base.Resource
import com.morostami.archsample.model.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinsListUseCase @Inject constructor(private val coinsListRepository: CoinsListRepository) {
    fun getCoinsList(): Flow<Resource<List<Coin>>> = coinsListRepository.getCoins()

    fun searchCoins(inputQuery: String) : Flow<PagingData<CoinEntity>> = coinsListRepository.searchCoins(inputQuery)
}