package com.morostami.archsample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.morostami.archsample.data.local.doa.BookmarksDao
import com.morostami.archsample.data.local.doa.CoinsDao
import com.morostami.archsample.data.local.doa.CryptoMarketDao
import com.morostami.archsample.data.local.doa.RemoteKeysDao
import com.morostami.archsample.domain.model.*

@Database(entities = [Coin::class, RankedCoin::class, CoinsRemoteKeys::class, Account::class], version = 3, exportSchema = false)
abstract class CryptoDataBase : RoomDatabase() {

    abstract fun coinDao() : CoinsDao
    abstract fun cryptoMarketDao() : CryptoMarketDao
    abstract fun remoteKeysDao() : RemoteKeysDao
    abstract fun bookmarksDao() : BookmarksDao
    companion object {
        @Volatile private var INSTANCE: CryptoDataBase? = null
        private const val db_name = "coins_db"

        fun getInstance(context: Context) : CryptoDataBase {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, CryptoDataBase::class.java, db_name)
                .build()
    }
}