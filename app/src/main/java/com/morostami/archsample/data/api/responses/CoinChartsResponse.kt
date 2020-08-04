/*
 * *
 *  * Created by Moslem Rostami on 7/26/20 8:05 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/26/20 8:05 AM
 *
 */

package com.morostami.archsample.data.api.responses

import com.google.gson.annotations.SerializedName
import com.morostami.archsample.data.local.entities.CoinPriceEntity
import com.morostami.archsample.data.local.entities.CoinMarketCapsEntity
import com.morostami.archsample.data.local.entities.CoinTotalVolumeEntity

data class CoinChartsResponse(
    @SerializedName("market_caps")
    var marketCaps: List<List<Double>>? = null,
    @SerializedName("prices")
    var prices: List<List<Double>>? = null,
    @SerializedName("total_volumes")
    var totalVolumes: List<List<Double>>? = null
) {
//    fun mapToCoinCharts(coinId: String, dPeriod: Int) = CoinCharts(
//        coinMarketCapsChart = marketCaps?.map { item ->
//            CoinMarketCapsEntity(
//                coinId,
//                dPeriod,
//                item[0].toLong(),
//                item[1]
//            )
//        },
//        pricesCharts = prices?.map { item ->
//            CoinPriceEntity(
//                coinId,
//                dPeriod,
//                item[0].toLong(),
//                item[1]
//            )
//        },
//        coinTotalVolumesCharts = totalVolumes?.map { item ->
//            CoinTotalVolumeEntity(
//                coinId,
//                dPeriod,
//                item[0].toLong(),
//                item[1]
//            )
//        }
//    )
}