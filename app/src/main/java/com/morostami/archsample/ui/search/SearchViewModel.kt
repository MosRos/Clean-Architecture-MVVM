/*
 * *
 *  * Created by Moslem Rostami on 7/10/20 12:28 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/10/20 12:28 AM
 *
 */

package com.morostami.archsample.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.morostami.archsample.domain.CoinsListUseCase
import com.morostami.archsample.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val coinsListUseCase: CoinsListUseCase) : ViewModel() {

    fun searchCoins(searchInput: String): Flow<PagingData<Coin>> = coinsListUseCase.searchCoins(searchInput)

}