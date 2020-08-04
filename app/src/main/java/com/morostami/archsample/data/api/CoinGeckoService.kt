/*
 * *
 *  * Created by Moslem Rostami on 3/19/20 12:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 12:50 PM
 *
 */

package com.morostami.archsample.data.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.responses.CoinChartsResponse
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.api.responses.CoinGeckoPingResponse
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.domain.model.RankedCoin
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("ping")
    suspend fun checkCoinGeckoConnection() : NetworkResponse<CoinGeckoPingResponse, CoinGeckoApiError>

    @GET("coins/list")
    suspend fun getCoins() : NetworkResponse<List<Coin>, CoinGeckoApiError>

    @GET("coins/markets")
    suspend fun getMarketRanks(@Query("vs_currency") vs_currency: String) : NetworkResponse<List<RankedCoin>, CoinGeckoApiError>

    @GET("coins/markets")
    suspend fun getPagedMarketRanks(
        @Query("vs_currency") vs_currency: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
        ) : List<RankedCoinEntity>



    @GET("coins/{id}")
    suspend fun getCoinInfo(
        @Path("id") id: String,
        @Query("localization") enableLocalization: Boolean = false,
        @Query("tickers") enableTickers: Boolean = false,
        @Query("market_data") enableMarketData: Boolean = false,
        @Query("community_data") enableCommunityData: Boolean = false,
        @Query("developer_data") enableDeveloperData: Boolean = false,
        @Query("sparkline") enableSparkline: Boolean = false
    ) : NetworkResponse<List<RankedCoin>, CoinGeckoApiError>

    @GET("coins/{id}/market_chart")
    suspend fun getCoinCharts(
        @Path("id") id: String,
        @Query("vs_currency") vs_currency: String,
        @Query("days") daysPeriod: Int
    ) : NetworkResponse<CoinChartsResponse, CoinGeckoApiError>

    @GET("coins/markets")
    suspend fun getBookMarks(
        @Query("vs_currency") vs_currency: String,
        @Query("ids") ids: List<String>) : NetworkResponse<List<RankedCoin>, CoinGeckoApiError>

    @GET("simple/supported_vs_currencies")
    suspend fun getSupportedVSCurrencies() : NetworkResponse<List<String>, CoinGeckoApiError>
}