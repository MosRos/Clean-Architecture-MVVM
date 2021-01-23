package com.morostami.archsample.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.morostami.archsample.R


fun AppCompatActivity.setStatusBarColor(color: Int)
{
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.setStatusBarColor(color)
}

fun AppCompatActivity.setStatusLightDark(is_light: Boolean)
{
    if (Build.VERSION.SDK_INT < 23) {
        return
    }
    var flags = window.decorView.systemUiVisibility
    if (is_light) {
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
    window.decorView.systemUiVisibility = flags
}

fun AppCompatActivity.setNavBarColor(color: Int)
{
    window.setNavigationBarColor(color)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
    {
        window.setNavigationBarDividerColor(color)
    }
}

fun AppCompatActivity.setNavBarLightDark(is_light: Boolean)
{
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        return
    }
    var flags = window.decorView.systemUiVisibility
    if (is_light) {
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    } else {
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
    }
    window.decorView.systemUiVisibility = flags
}

fun setLightStatusBar(view: View, activity: Activity, colorRsId: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

    } else{
        activity.window.statusBarColor = ContextCompat.getColor(activity.baseContext, R.color.divider_color)
        activity.window.navigationBarColor = ContextCompat.getColor(activity.baseContext, R.color.divider_color)
    }
}

fun clearLightStatusBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val window = activity.window
        window.statusBarColor = ContextCompat.getColor(activity, R.color.color_primary)
    }
}