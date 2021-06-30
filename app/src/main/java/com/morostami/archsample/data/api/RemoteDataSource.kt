/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 2:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 2:16 PM
 *
 */

package com.morostami.archsample.data.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.responses.CoinChartsResponse
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.api.responses.CoinGeckoPingResponse
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import com.morostami.archsample.model.Coin
import com.morostami.archsample.model.RankedCoin
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofit: Retrofit) : CoinGeckoService {

    private val apiService by lazy { retrofit.create(CoinGeckoService::class.java) }

    override suspend fun checkCoinGeckoConnection(): NetworkResponse<CoinGeckoPingResponse, CoinGeckoApiError> = apiService.checkCoinGeckoConnection()

    override suspend fun getCoins(): NetworkResponse<List<Coin>, CoinGeckoApiError> = apiService.getCoins()

    override suspend fun getMarketRanks(vs_currency: String): NetworkResponse<List<RankedCoin>, CoinGeckoApiError> = apiService.getMarketRanks(vs_currency)

    override suspend fun getPagedMarketRanks(
        vs_currency: String,
        page: Int,
        per_page: Int
    ): List<RankedCoinEntity> = apiService.getPagedMarketRanks(vs_currency = vs_currency, page = page, per_page = per_page)

    override suspend fun getCoinInfo(
        id: String,
        enableLocalization: Boolean,
        enableTickers: Boolean,
        enableMarketData: Boolean,
        enableCommunityData: Boolean,
        enableDeveloperData: Boolean,
        enableSparkline: Boolean
    ): NetworkResponse<List<RankedCoin>, CoinGeckoApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinCharts(
        id: String,
        vs_currency: String,
        daysPeriod: Int
    ): NetworkResponse<CoinChartsResponse, CoinGeckoApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookMarks(
        vs_currency: String,
        ids: List<String>
    ): NetworkResponse<List<RankedCoin>, CoinGeckoApiError> = apiService.getBookMarks(vs_currency = vs_currency, ids = ids)

    override suspend fun getSupportedVSCurrencies(): NetworkResponse<List<String>, CoinGeckoApiError> = apiService.getSupportedVSCurrencies()


}