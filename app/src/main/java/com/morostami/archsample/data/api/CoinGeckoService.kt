/*
 * *
 *  * Created by Moslem Rostami on 3/19/20 12:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 12:50 PM
 *
 */

package com.morostami.archsample.data.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.api.responses.CoinGeckoPingResponse
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.domain.model.RankedCoin
import retrofit2.http.GET
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
        ) : List<RankedCoin>

    @GET("coins/markets")
    suspend fun getBookMarks(
        @Query("vs_currency") vs_currency: String,
        @Query("ids") ids: List<String>) : NetworkResponse<List<RankedCoin>, CoinGeckoApiError>

    @GET("simple/supported_vs_currencies")
    suspend fun getSupportedVSCurrencies() : NetworkResponse<List<String>, CoinGeckoApiError>
}