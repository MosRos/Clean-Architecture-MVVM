package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.Coin

/**
 * Created by Moslem Rostami on $today
 * Copyright (c) $today.year . All rights reserved.
 * Last modified $file.lastModified
 */

interface CoinsCRUD {

    fun insertCoins(coinsList: List<Coin>)

    fun insertCoin(coin: Coin)

    fun getAllCoins() : List<Coin>

    fun getCoinsList() : List<Coin>

    fun deletCoin(coin: Coin)

    fun deletCoins(coinsList: List<Coin>)

}