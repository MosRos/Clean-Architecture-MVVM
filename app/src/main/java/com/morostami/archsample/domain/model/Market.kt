/*
 * *
 *  * Created by Moslem Rostami on 3/19/20 3:26 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 3:26 PM
 *
 */

package com.morostami.archsample.domain.model


import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("ath")
    var ath: Double? = null, // 19665.39
    @SerializedName("ath_change_percentage")
    var athChangePercentage: Double? = null, // -71.71309
    @SerializedName("ath_date")
    var athDate: String? = null, // 2017-12-16T00:00:00.000Z
    @SerializedName("circulating_supply")
    var circulatingSupply: Double? = null, // 18277325.0
    @SerializedName("current_price")
    var currentPrice: Double? = null, // 5572.89
    @SerializedName("high_24h")
    var high24h: Double? = null, // 5618.43
    @SerializedName("id")
    var id: String? = null, // bitcoin
    @SerializedName("image")
    var image: String? = null, // https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579
    @SerializedName("last_updated")
    var lastUpdated: String? = null, // 2020-03-19T11:50:22.168Z
    @SerializedName("low_24h")
    var low24h: Double? = null, // 5036.93
    @SerializedName("market_cap")
    var marketCap: Long? = null, // 101698446823
    @SerializedName("market_cap_change_24h")
    var marketCapChange24h: Long? = null, // 7642522200
    @SerializedName("market_cap_change_percentage_24h")
    var marketCapChangePercentage24h: Double? = null, // 8.12551
    @SerializedName("market_cap_rank")
    var marketCapRank: Int? = null, // 1
    @SerializedName("name")
    var name: String? = null, // Bitcoin
    @SerializedName("price_change_24h")
    var priceChange24h: Double? = null, // 438.0
    @SerializedName("price_change_percentage_24h")
    var priceChangePercentage24h: Double? = null, // 8.52989
    @SerializedName("roi")
    var roi: Any? = null, // null
    @SerializedName("symbol")
    var symbol: String? = null, // btc
    @SerializedName("total_supply")
    var totalSupply: Double? = null, // 21000000.0
    @SerializedName("total_volume")
    var totalVolume: Long? = null // 40563276914
)