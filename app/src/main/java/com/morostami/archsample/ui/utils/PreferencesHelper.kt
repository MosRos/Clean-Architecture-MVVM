/*
 * *
 *  * Created by Moslem Rostami on 3/27/20 2:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/27/20 2:34 AM
 *
 */

package com.morostami.archsample.ui.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class PreferencesHelper @Inject constructor(private val preferences: SharedPreferences) {

    /**
     * SharedPreferences extension function, so we won't need to call edit()
    and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation:
                                                  (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

//    private const val NAME = "NobatYaar"
//    private const val MODE = Context.MODE_PRIVATE
//    private lateinit var preferences: SharedPreferences
//
//    fun init(context: Context) {
//        Log.e("PREFERENCES", "PREFERENCE INITIALIZED")
//        preferences = context.getSharedPreferences(NAME, MODE)
//    }

//    fun getPreferences() : SharedPreferences {
//        return preferences
//    }

    private val SELECTED_THEME_MODE = Pair("theme-mode", 0)
    var selectedThemeMode: Int
        get() = preferences.getInt(SELECTED_THEME_MODE.first, SELECTED_THEME_MODE.second)
        set(value) = preferences.edit{it.putInt(SELECTED_THEME_MODE.first, value) }
}