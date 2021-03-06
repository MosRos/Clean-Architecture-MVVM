/*
 * *
 *  * Created by Moslem Rostami on 4/11/20 1:23 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/11/20 1:23 AM
 *
 */

package com.morostami.archsample.data.prefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

const val THEME_MODE_KEY = "theme-mode"

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

    fun getInstance() : SharedPreferences {
        return preferences
    }

    val SELECTED_THEME_MODE = Pair(THEME_MODE_KEY, AppCompatDelegate.MODE_NIGHT_NO)
    var selectedThemeMode: Int
        get() = preferences.getInt(SELECTED_THEME_MODE.first, SELECTED_THEME_MODE.second)
        set(value) = preferences.edit{it.putInt(SELECTED_THEME_MODE.first, value) }
}