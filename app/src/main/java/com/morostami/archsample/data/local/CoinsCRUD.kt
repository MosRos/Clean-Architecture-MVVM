package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.Coin

/**
 * Created by Moslem Rostami on $today
 * Copyright (c) $today.year . All rights reserved.
 * Last modified $file.lastModified
 */

interface CoinsCRUD {

    suspend fun insertCoins(coinsList: List<Coin>)

    suspend fun insertCoin(coin: Coin)

    suspend fun getAllCoins() : List<Coin>

    suspend fun getCoinsList() : List<Coin>

    suspend fun deletCoin(coin: Coin)

    suspend fun deletCoins(coinsList: List<Coin>)

}