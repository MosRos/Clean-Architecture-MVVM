package com.morostami.archsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morostami.archsample.data.local.doa.CoinsDao
import com.morostami.archsample.data.local.doa.CryptoMarketDao
import com.morostami.archsample.data.local.doa.RemoteKeysDao
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.domain.model.CoinsRemoteKeys
import com.morostami.archsample.domain.model.RankedCoin

@Database(entities = [Coin::class, RankedCoin::class, CoinsRemoteKeys::class], version = 2, exportSchema = false)
abstract class CryptoDataBase : RoomDatabase() {

    abstract fun coinDao() : CoinsDao
    abstract fun cryptoMarketDao() : CryptoMarketDao
    abstract fun remoteKeysDao() : RemoteKeysDao
//    companion object {
//        @Volatile private var INSTANCE: CoinsRoomDataBase? = null
//        private const val db_name = "coins_db"
//
//        fun getInstance(context: Context) : CoinsRoomDataBase {
//            return INSTANCE ?: synchronized(this){
//                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
//            }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, CoinsRoomDataBase::class.java, db_name)
//                .build()
//    }
}