package com.morostami.archsample.ui.base

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import com.roundtableapps.cloudrake.common.base.BaseActivity

interface Navigator {
    fun navigateTo(navDirections: NavDirections)
    fun navigateTo(navDirections: Uri)
    fun popBackStackToRoot()
    fun popBackStack(@IdRes destinationId: Int, inclusive: Boolean)
    fun popBackStack()

}

object NavigatorConst{
    const val SAMPLE = "sample"

}

object NavigatorActions {

}

fun Context?.navigateTo(navDirections: NavDirections) {
    (this as BaseActivity<*>?)?.navigateTo(navDirections = navDirections)
}

fun View?.navigateTo(navDirections: NavDirections) {
    (this?.context as BaseActivity<*>?)?.navigateTo(navDirections = navDirections)
}