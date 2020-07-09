package com.morostami.archsample.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.domain.model.RankedCoin

@Dao
interface CoinsRoomDao : CoinsCRUD {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertCoins(coinsList: List<Coin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertCoin(coin: Coin)

    @Query("SELECT * FROM Coin")
    override suspend fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM Coin")
    override suspend fun getCoinsList(): List<Coin>

    @Query("SELECT * FROM Coin WHERE symbol LIKE :input || '%' OR name LIKE :input || '%'")
    suspend fun searchCoins(input: String): List<Coin>

    @Query("SELECT * FROM Coin WHERE symbol LIKE :input || '%' OR name LIKE :input || '%' ORDER BY symbol ASC")
    fun searchPagedCoins(input: String): PagingSource<Int, Coin>

    @Query("SELECT * FROM Coin ORDER BY symbol ASC")
    fun getPagedCoins(): PagingSource<Int, Coin>

    @Delete
    override suspend fun deletCoin(coin: Coin)

    @Delete
    override suspend fun deletCoins(coinsList: List<Coin>)
}