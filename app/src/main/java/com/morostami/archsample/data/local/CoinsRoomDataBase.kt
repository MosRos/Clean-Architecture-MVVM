package com.morostami.archsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morostami.archsample.domain.model.Coin

@Database(entities = arrayOf(Coin::class), version = 1, exportSchema = false)
abstract class CoinsRoomDataBase : RoomDatabase() {

    abstract fun coindDao() : CoinsRoomDao
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