/*
 * *
 *  * Created by Moslem Rostami on 8/2/20 1:19 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 1:19 PM
 *
 */

package com.morostami.archsample.data.local.converters

import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.data.local.entities.RankedCoinEntity
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.domain.model.RankedCoin

fun Coin.toEntity() : CoinEntity {
    return CoinEntity(
        id = this.id,
        name = this.name,
        symbol = this.symbol
    )
}

fun CoinEntity.toCoin() = Coin(
        id = this.id,
        name = this.name,
        symbol = this.symbol
    )

fun RankedCoin.toEntity() : RankedCoinEntity = RankedCoinEntity(
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    circulatingSupply = this.circulatingSupply,
    currentPrice = this.currentPrice,
    high24h = this.high24h,
    id = this.id,
    image = this.image,
    lastUpdated = this.lastUpdated,
    low24h = this.low24h,
    marketCap = this.marketCap,
    marketCapChange24h = this.marketCapChange24h,
    marketCapChangePercentage24h = this.marketCapChangePercentage24h,
    marketCapRank = this.marketCapRank,
    name = this.name,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    roi = this.roi,
    symbol = this.symbol,
    totalSupply = this.totalSupply,
    totalVolume = this.totalVolume,
    accountBookId = ""
)

fun RankedCoinEntity.toRankedCoin() : RankedCoin = RankedCoin(
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    circulatingSupply = this.circulatingSupply,
    currentPrice = this.currentPrice,
    high24h = this.high24h,
    id = this.id,
    image = this.image,
    lastUpdated = this.lastUpdated,
    low24h = this.low24h,
    marketCap = this.marketCap,
    marketCapChange24h = this.marketCapChange24h,
    marketCapChangePercentage24h = this.marketCapChangePercentage24h,
    marketCapRank = this.marketCapRank,
    name = this.name,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    roi = this.roi,
    symbol = this.symbol,
    totalSupply = this.totalSupply,
    totalVolume = this.totalVolume
)