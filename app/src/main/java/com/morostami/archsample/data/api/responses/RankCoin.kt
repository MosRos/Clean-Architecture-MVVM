/*
 * *
 *  * Created by Moslem Rostami on 8/5/20 1:12 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/5/20 1:12 AM
 *
 */

package com.morostami.archsample.data.api.responses


import com.google.gson.annotations.SerializedName

data class RankCoin(
 @SerializedName("ath")
 var ath: Double? = null, // 1448.18
 @SerializedName("ath_change_percentage")
 var athChangePercentage: Double? = null, // -73.09333
 @SerializedName("ath_date")
 var athDate: String? = null, // 2018-01-13T00:00:00.000Z
 @SerializedName("atl")
 var atl: Double? = null, // 0.432979
 @SerializedName("atl_change_percentage")
 var atlChangePercentage: Double? = null, // 89894.52683
 @SerializedName("atl_date")
 var atlDate: String? = null, // 2015-10-20T00:00:00.000Z
 @SerializedName("circulating_supply")
 var circulatingSupply: Double? = null, // 112044375.374
 @SerializedName("current_price")
 var currentPrice: Double? = null, // 389.08
 @SerializedName("high_24h")
 var high24h: Double? = null, // 400.25
 @SerializedName("id")
 var id: String? = null, // ethereum
 @SerializedName("image")
 var image: String? = null, // https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880
 @SerializedName("last_updated")
 var lastUpdated: String? = null, // 2020-08-04T20:36:30.132Z
 @SerializedName("low_24h")
 var low24h: Double? = null, // 381.97
 @SerializedName("market_cap")
 var marketCap: Long? = null, // 43635364503
 @SerializedName("market_cap_change_24h")
 var marketCapChange24h: Double? = null, // -296141046.00077057
 @SerializedName("market_cap_change_percentage_24h")
 var marketCapChangePercentage24h: Double? = null, // -0.6741
 @SerializedName("market_cap_rank")
 var marketCapRank: Int? = null, // 2
 @SerializedName("name")
 var name: String? = null, // Ethereum
 @SerializedName("price_change_24h")
 var priceChange24h: Double? = null, // -1.52998995
 @SerializedName("price_change_percentage_24h")
 var priceChangePercentage24h: Double? = null, // -0.39169
 @SerializedName("roi")
 var roi: Roi? = null,
 @SerializedName("symbol")
 var symbol: String? = null, // eth
 @SerializedName("total_supply")
 var totalSupply: Any? = null, // null
 @SerializedName("total_volume")
 var totalVolume: Long? = null // 13472929769
)