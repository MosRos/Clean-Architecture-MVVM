/*
 * *
 *  * Created by Moslem Rostami on 3/23/20 10:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/7/20 1:31 AM
 *
 */

package com.morostami.archsample.ui

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.morostami.archsample.MainApp
import com.morostami.archsample.R
import com.morostami.archsample.data.prefs.PreferencesHelper
import com.morostami.archsample.databinding.ActivityMainBinding
import com.morostami.archsample.di.CoinsComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    private lateinit var databinding: ActivityMainBinding
    lateinit var coinsComponent: CoinsComponent
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    private lateinit var themeDialog: AlertDialog
    private var selectedTheme: Int = AppCompatDelegate.MODE_NIGHT_NO

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

        initBottomNavAndController()
        setObservers()
        setListeners()
    }

    private fun initBottomNavAndController() {
        bottomNav = databinding.bottomNavView
        navController = Navigation.findNavController(this, R.id.nav_host_container)
        NavigationUI.setupWithNavController(bottomNav, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.label != null) {
                mainViewModel.fragName.set(destination.label.toString())
            }

            if (destination.id == R.id.navigation_search){
                databinding.appBarLayout.visibility = View.GONE
            } else {
                databinding.appBarLayout.visibility = View.VISIBLE
            }
        }
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
