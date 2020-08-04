package com.morostami.archsample.data.local.doa

import androidx.paging.PagingSource
import androidx.room.*
import com.morostami.archsample.data.local.entities.CoinEntity

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coinsList: List<CoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: CoinEntity)

    @Query("SELECT * FROM Coin")
    suspend fun getAllCoins(): List<CoinEntity>

    @Query("SELECT * FROM Coin")
    suspend fun getCoinsList(): List<CoinEntity>

    @Delete
    suspend fun deletCoin(coin: CoinEntity)

    @Delete
    suspend fun deletCoins(coinsList: List<CoinEntity>)

    @Query("SELECT * FROM Coin WHERE symbol LIKE :input || '%' OR name LIKE :input || '%'")
    suspend fun searchCoins(input: String): List<CoinEntity>

    @Query("SELECT * FROM Coin WHERE symbol LIKE :input || '%' OR name LIKE :input || '%' ORDER BY symbol ASC")
    fun searchPagedCoins(input: String): PagingSource<Int, CoinEntity>

    @Query("SELECT * FROM Coin ORDER BY symbol ASC")
    fun getPagedCoins(): PagingSource<Int, CoinEntity>
}