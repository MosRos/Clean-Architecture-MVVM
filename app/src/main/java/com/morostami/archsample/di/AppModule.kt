/*
 * *
 *  * Created by Moslem Rostami on 3/18/20 1:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 1:48 PM
 *
 */

package com.morostami.archsample.di

import android.app.Application
import androidx.room.Room
import com.morostami.archsample.data.local.CoinsRoomDao
import com.morostami.archsample.data.local.CoinsRoomDataBase
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, NetModule::class, DataModule::class])
class AppModule {

}