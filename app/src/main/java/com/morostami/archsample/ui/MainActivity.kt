/*
 * *
 *  * Created by Moslem Rostami on 3/23/20 10:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/7/20 1:31 AM
 *
 */

package com.morostami.archsample.ui

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.morostami.archsample.MainApp
import com.morostami.archsample.R
import com.morostami.archsample.databinding.ActivityMainBinding
import com.morostami.archsample.di.CoinsComponent
import com.morostami.archsample.ui.utils.PreferencesHelper
import com.morostami.archsample.ui.utils.booleanLiveData
import com.morostami.archsample.ui.utils.intLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


const val KEY_THEME_MODE = "theme-mode"
val THEME_MAPPINGS = mapOf(
    1 to AppCompatDelegate.MODE_NIGHT_NO,
    2 to AppCompatDelegate.MODE_NIGHT_YES,
    3 to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
)
val THEME_ICON_MAPPINGS = mapOf(
    R.drawable.ic_sun to AppCompatDelegate.MODE_NIGHT_NO,
    R.drawable.ic_moon to AppCompatDelegate.MODE_NIGHT_YES,
    R.drawable.ic_brightness_auto to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
)

class MainActivity : AppCompatActivity() {

    lateinit var databinding: ActivityMainBinding
    lateinit var coinsComponent: CoinsComponent
    lateinit var themeDialog: AlertDialog
    var selectedTheme: Int = AppCompatDelegate.MODE_NIGHT_NO

    @Inject
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        coinsComponent = (application as MainApp).appComponent.coinsComponent().create()
        coinsComponent.injectMainActivity(this)

        selectedTheme = preferencesHelper.selectedThemeMode
        AppCompatDelegate.setDefaultNightMode(selectedTheme)
        if (selectedTheme == 2){

        }
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        databinding.lifecycleOwner = this
        databinding.viewmodel = mainViewModel
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        when (selectedTheme) {
            AppCompatDelegate.MODE_NIGHT_NO -> setThemeIcon(R.drawable.ic_sun)

            AppCompatDelegate.MODE_NIGHT_YES -> setThemeIcon(R.drawable.ic_moon)

            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> setThemeIcon(R.drawable.ic_brightness_auto)
            else -> setThemeIcon(R.drawable.ic_brightness_auto)
        }
    }

    private fun setThemeIcon(@DrawableRes iconId: Int) {
        databinding.themeSelectIcon.setImageDrawable(ContextCompat.getDrawable(this, iconId))
    }

    private fun setListeners() {
        databinding.themeSelectIcon.setOnClickListener {
            when(selectedTheme) {
                -1 -> selectedTheme = 3
            }
            themeDialog = AlertDialog.Builder(this)
                .setTitle("Please Select Theme")
                .setSingleChoiceItems(
                    arrayOf("Light", "Dark", "Auto"), selectedTheme - 1
                ) { dialogInterface: DialogInterface?, i: Int -> applyTheme(i) }
                .show()
        }
    }

    private fun applyTheme(selected: Int) {
        when (selected) {
            0 -> preferencesHelper.selectedThemeMode = 1
            1 -> preferencesHelper.selectedThemeMode = 2
            2 -> preferencesHelper.selectedThemeMode = -1
            else -> preferencesHelper.selectedThemeMode = -1
        }
        themeDialog.dismiss()
        lifecycleScope.launch {
            delay(200)
        }
        this.recreate()
    }
}
