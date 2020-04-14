/*
 * *
 *  * Created by Moslem Rostami on 3/23/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/23/20 10:51 PM
 *
 */

package com.morostami.archsample.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.morostami.archsample.di.ActivityScope
import com.morostami.archsample.domain.CoinsListUseCase
import com.morostami.archsample.domain.CryptoMarketUseCase
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.utils.LoadingState
import com.morostami.archsample.utils.Resource
import com.morostami.archsample.utils.invoke
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(private val coinsListUseCase: CoinsListUseCase) : ViewModel() {
    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    val fragName: ObservableField<String> = ObservableField("Home")

    var coinsList: LiveData<List<Coin>> = MutableLiveData(ArrayList())
    var coinSearchResults: LiveData<List<Coin>> = MutableLiveData()

    init {
        getCoinsList()
    }

    private fun getCoinsList() {
        _loading.value = LoadingState.LOADING

        coinsList = liveData {
            coinsListUseCase.getCoinsList().collect {coinsRes ->
                when(coinsRes) {
                    is Resource.Success -> {
                        Timber.e(coinsRes.invoke().size.toString())
                        emit(coinsRes.invoke())
                        setLoadingState(LoadingState.LOADED)
                    }
                    is Resource.Error<*, *> -> {
                        Timber.e(coinsRes.error.toString())
                        setLoadingState(LoadingState.error("fuck"))
                    }
                    is Resource.Loading -> { setLoadingState(LoadingState.LOADING) }
                }
            }
        }
    }

    fun searchCoins(searchInput: String) {
        _loading.value = LoadingState.LOADING

        if (searchInput.isEmpty()){
            coinSearchResults = coinsList
            setLoadingState(LoadingState.LOADED)
            return
        }

        coinSearchResults = liveData {
            coinsListUseCase.searchCoins(searchInput).collect {coinsRes ->
                when(coinsRes) {
                    is Resource.Success -> {
                        Timber.e(coinsRes.invoke().size.toString())
                        emit(coinsRes.invoke())
                        setLoadingState(LoadingState.LOADED)
                    }
                    is Resource.Error<*, *> -> {
                        Timber.e(coinsRes.error.toString())
                        setLoadingState(LoadingState.error("fuck"))
                    }
                    is Resource.Loading -> { setLoadingState(LoadingState.LOADING) }
                }
            }
        }
    }

    private fun setLoadingState(state: LoadingState) {
        _loading.value = state
    }
}