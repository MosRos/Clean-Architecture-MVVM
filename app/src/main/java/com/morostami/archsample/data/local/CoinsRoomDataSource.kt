package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.Coin
import javax.inject.Inject

class CoinsRoomDataSource @Inject constructor(dataBase: CoinsRoomDataBase) : CoinsCRUD {

    private val coinsRoomDao: CoinsRoomDao = dataBase.coindDao()

    override suspend fun insertCoins(coinsList: List<Coin>) {
        coinsRoomDao.insertCoins(coinsList)
    }

    override suspend fun insertCoin(coin: Coin) {
        coinsRoomDao.insertCoin(coin)
    }

    override suspend fun getAllCoins(): List<Coin> {
        return coinsRoomDao.getAllCoins()
    }

    override suspend fun getCoinsList(): List<Coin> {
        return coinsRoomDao.getCoinsList()
    }

    override suspend fun deletCoin(coin: Coin) {
        coinsRoomDao.deletCoin(coin)
    }

    override suspend fun deletCoins(coinsList: List<Coin>) {
        coinsRoomDao.deletCoins(coinsList)
    }
}