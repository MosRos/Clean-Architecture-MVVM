/*
 * *
 *  * Created by Moslem Rostami on 4/10/20 8:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/10/20 8:06 PM
 *
 */

package com.morostami.archsample.ui.marketrank

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.morostami.archsample.di.ActivityScope
import com.morostami.archsample.domain.CryptoMarketUseCase
import com.morostami.archsample.domain.MarketRanksUseCase
import com.morostami.archsample.domain.model.RankedCoin
import com.morostami.archsample.utils.LoadingState
import com.morostami.archsample.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class MarketViewModel @Inject constructor(val cryptoMarketUseCase: CryptoMarketUseCase, val marketRanksUseCase: MarketRanksUseCase) : ViewModel() {

    private val _rankLoading = MutableLiveData<LoadingState>()
    val ranksLoading: LiveData<LoadingState>
        get() = _rankLoading

    var marketRankedList: LiveData<List<RankedCoin>> = MutableLiveData(ArrayList())

    var pagedMarketRanks: LiveData<PagingData<RankedCoin>> = MutableLiveData<PagingData<RankedCoin>>()

    init {
//        viewModelScope.launch {
////            getMarketRanks()
//            getPagedRankedCoins()
//        }
    }

    fun getMarketRanks() {
        _rankLoading.value = LoadingState.LOADING
        marketRankedList = liveData {
            Timber.e("Send Request For ranks")
            cryptoMarketUseCase.getMarketRanks().collect {rankedResource ->
                when(rankedResource) {
                    is Resource.Success -> {
                        Timber.e("collected ranks ${rankedResource.data.size}")
                        emit(rankedResource.invoke())
                        setLoadingState(LoadingState.LOADED)
                    }

                    is Resource.Error<*,*> -> {
                        Timber.e("error collecting market ranks")
                        setLoadingState(LoadingState.error("shit!!!"))
                    }

                    is Resource.Loading -> {
                        setLoadingState(LoadingState.LOADING)
                    }
                }
            }
        }
    }

    fun getPagedRankedCoins() : Flow<PagingData<RankedCoin>> = flow {
        _rankLoading.value = LoadingState.LOADING
        emitAll(marketRanksUseCase.getPagedRanks())
    }

    private fun setLoadingState(state: LoadingState) {
        _rankLoading.value = state
    }

}