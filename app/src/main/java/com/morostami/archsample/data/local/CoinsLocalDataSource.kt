package com.morostami.archsample.data.local

import com.morostami.archsample.data.local.doa.CoinsDao
import com.morostami.archsample.domain.model.Coin
import javax.inject.Inject

class CoinsLocalDataSource @Inject constructor(private val cryptoMarketDataBase: CryptoDataBase) :
    CoinsDao {

    private val coinsDao by lazy { cryptoMarketDataBase.coinDao() }

    override suspend fun insertCoins(coinsList: List<Coin>) = coinsDao.insertCoins(coinsList)

    override suspend fun insertCoin(coin: Coin) = coinsDao.insertCoin(coin)

    override suspend fun getAllCoins(): List<Coin> = coinsDao.getAllCoins()

    override suspend fun getCoinsList(): List<Coin> = coinsDao.getCoinsList()

    override suspend fun deletCoin(coin: Coin) = coinsDao.deletCoin(coin)

    override suspend fun deletCoins(coinsList: List<Coin>) = coinsDao.deletCoins(coinsList)

    override suspend fun searchCoins(searchQuery: String) = coinsDao.searchCoins(searchQuery)

    override fun searchPagedCoins(searchQuery: String) = coinsDao.searchPagedCoins(searchQuery)

    override fun getPagedCoins() = coinsDao.getPagedCoins()
}