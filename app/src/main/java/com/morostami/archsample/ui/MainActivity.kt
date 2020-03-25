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
import com.morostami.archsample.MainApp
import com.morostami.archsample.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApp).appComponent.injectMainActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
