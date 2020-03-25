package com.morostami.archsample.data.local

import androidx.room.*
import com.morostami.archsample.domain.model.Coin

@Dao
interface CoinsRoomDao : CoinsCRUD {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCoins(coinsList: List<Coin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCoin(coin: Coin)

    @Query("SELECT * FROM Coin")
    override fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM Coin")
    override fun getCoinsList(): List<Coin>

    @Delete
    override fun deletCoin(coin: Coin)

    @Delete
    override fun deletCoins(coinsList: List<Coin>)
}