package com.morostami.archsample.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.TypedValue
import android.view.View
import com.morostami.archsample.MainApp

class UtilExtensions {

}

fun dpToPx(dp: Float) =
        TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                MainApp.getInstance().resources.displayMetrics
        )

fun pxToDp(px: Int): Int {
    val r: Resources = MainApp.getInstance().resources
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            px.toFloat(),
            r.displayMetrics
    ).toInt()
}
fun openLinkInBrowser(context: Context?, url: String) {

    val safeUrl: String = if (!url.startsWith("http://") && !url.startsWith("https://")) {
        "http://${url.trimStart().trim()}"
    } else {
        url
    }
    val intent =
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(safeUrl)
            }
    try {
        context?.startActivity(intent)
    } catch (e: Exception) {
//        "Start Browser Intent ${e.message}".log("Global Functions")
    }
}

inline fun <reified T: View> View.findViewInHierarchy(): T? =
        (findViewWithSpecificParent<T>()?.parent as? T)

inline fun <reified T: View> View.findViewWithSpecificParent(): View? =
        findViewWithSpecificParent(T::class.java)

@Suppress("UNCHECKED_CAST")
fun <T: View> View.findViewWithSpecificParent(clazz: Class<T>): View? {
    if (parent == null) return null
    if (parent::class.java == clazz) return this
    return (parent as View).findViewWithSpecificParent(clazz)
}

fun Float.toRadians(): Float {
    return (this *  Math.PI.toFloat()) / 180f
}