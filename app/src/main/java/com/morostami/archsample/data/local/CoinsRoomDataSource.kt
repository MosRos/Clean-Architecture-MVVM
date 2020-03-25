package com.morostami.archsample.data.local

import com.morostami.archsample.domain.model.Coin
import javax.inject.Inject

class CoinsRoomDataSource @Inject constructor(dataBase: CoinsRoomDataBase) : CoinsCRUD {

    private val coinsRoomDao: CoinsRoomDao = dataBase.coindDao()

    override fun insertCoins(coinsList: List<Coin>) {
        coinsRoomDao.insertCoins(coinsList)
    }

    override fun insertCoin(coin: Coin) {
        coinsRoomDao.insertCoin(coin)
    }

    override fun getAllCoins(): List<Coin> {
        return coinsRoomDao.getAllCoins()
    }

    override fun getCoinsList(): List<Coin> {
        return coinsRoomDao.getCoinsList()
    }

    override fun deletCoin(coin: Coin) {
        coinsRoomDao.deletCoin(coin)
    }

    override fun deletCoins(coinsList: List<Coin>) {
        coinsRoomDao.deletCoins(coinsList)
    }
}