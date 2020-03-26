/*
 * *
 *  * Created by Moslem Rostami on 3/23/20 10:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/7/20 1:31 AM
 *
 */

package com.morostami.archsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.morostami.archsample.MainApp
import com.morostami.archsample.R
import com.morostami.archsample.di.CoinsComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var coinsComponent: CoinsComponent

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        coinsComponent = (application as MainApp).appComponent.coinsComponent().create()
        coinsComponent.injectMainActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
