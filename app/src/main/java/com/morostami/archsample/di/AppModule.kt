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
import com.morostami.archsample.data.prefs.PreferencesHelper
import dagger.Module
import dagger.Provides
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
        return PreferencesHelper(
            sharedPreferences
        )
    }
}