package com.morostami.archsample.data.local

import androidx.paging.PagingSource
import com.morostami.archsample.data.local.doa.CoinsDao
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.domain.model.Coin
import javax.inject.Inject

class CoinsLocalDataSource @Inject constructor(private val cryptoMarketDataBase: CryptoDataBase) :
    CoinsDao {

    private val coinsDao by lazy { cryptoMarketDataBase.coinDao() }

    override suspend fun insertCoins(coinsList: List<CoinEntity>) = coinsDao.insertCoins(coinsList)

    override suspend fun insertCoin(coin: CoinEntity) = coinsDao.insertCoin(coin)

    override suspend fun getAllCoins(): List<CoinEntity> = coinsDao.getAllCoins()

    override suspend fun getCoinsList(): List<CoinEntity> = coinsDao.getCoinsList()

    override suspend fun deletCoin(coin: CoinEntity) = coinsDao.deletCoin(coin)

    override suspend fun deletCoins(coinsList: List<CoinEntity>) = coinsDao.deletCoins(coinsList)

    override suspend fun searchCoins(searchQuery: String) = coinsDao.searchCoins(searchQuery)

    override fun searchPagedCoins(searchQuery: String) = coinsDao.searchPagedCoins(searchQuery)

    override fun getPagedCoins() = coinsDao.getPagedCoins()
}