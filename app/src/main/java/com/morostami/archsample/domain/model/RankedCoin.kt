/*
 * *
 *  * Created by Moslem Rostami on 3/19/20 3:26 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 3:26 PM
 *
 */

package com.morostami.archsample.domain.model


data class RankedCoin(
    var ath: Double?,
    var athChangePercentage: Double?,
    var athDate: String?, // 2018-01-13T00:00:00.000Z
    var circulatingSupply: Double?, // 110463081.8115
    var currentPrice: Double?, // 156.44
    var high24h: Double?, // 170.85
    var id: String = "xxxbtcu", // ethereum
    var image: String?, // https://assets.coingecko.com/coins/images/279/large/ethereum.png?1547034048
    var lastUpdated: String?, // 2020-04-10T18:27:39.161Z
    var low24h: Double?, // 153.71
    var marketCap: Long?, // 17294979690
    var marketCapChange24h: Double?, // -1441856599.09626
    var marketCapChangePercentage24h: Double?, // -7.6953
    var marketCapRank: Int?, // 2
    var name: String?, // Ethereum
    var priceChange24h: Double?, // -13.44174154
    var priceChangePercentage24h: Double?, // -7.91249
    var roi: Roi?,
    var symbol: String?, // eth
    var totalSupply: Double?, // null
    var totalVolume: Long?
)