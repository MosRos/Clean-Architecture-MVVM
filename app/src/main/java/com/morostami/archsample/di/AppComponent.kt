/*
 * *
 *  * Created by Moslem Rostami on 3/22/20 8:00 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/22/20 8:00 PM
 *
 */

package com.morostami.archsample.di

import android.app.Application
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetModule::class, DataModule::class, AppSubComponents::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }

    fun coinsComponent() : CoinsComponent.Factory
}