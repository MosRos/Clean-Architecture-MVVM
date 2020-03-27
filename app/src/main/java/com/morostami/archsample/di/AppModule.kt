/*
 * *
 *  * Created by Moslem Rostami on 3/18/20 1:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/18/20 1:48 PM
 *
 */

package com.morostami.archsample.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.morostami.archsample.MainApp
import com.morostami.archsample.data.local.CoinsRoomDao
import com.morostami.archsample.data.local.CoinsRoomDataBase
import com.morostami.archsample.ui.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application) : SharedPreferences {
        return app.getSharedPreferences("arch_sample", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferenceHelper(sharedPreferences: SharedPreferences) : PreferencesHelper {
        return PreferencesHelper(sharedPreferences)
    }
}