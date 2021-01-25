package com.roundtableapps.cloudrake.common.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.morostami.archsample.R
import com.morostami.archsample.ui.base.Navigator
import com.morostami.archsample.utils.dpToPx
import com.morostami.archsample.utils.findViewWithSpecificParent
import kotlin.reflect.KProperty


abstract class BaseBottomSheetFragment<V : BaseViewModel>(
    private val mExpandState: Boolean = true,
    private val expandOffset: Int? = null
): BottomSheetDialogFragment(),
    DialogInterface.OnShowListener,
    Navigator {

    abstract val viewModel: V
    abstract val viewId : Int

    private var mNavigator: Navigator? = null

    var behavior: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewId, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModels()
    }

    protected open fun setupViewModels() {

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(dialog)
        }
        return dialog
    }

    override fun onShow(dialog: DialogInterface?) {
        view?.findViewWithSpecificParent<CoordinatorLayout>()?.let {
            if (mExpandState) {
                it.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                it.requestLayout()
            }
            behavior = BottomSheetBehavior.from(it).apply {
                if (mExpandState) {
                    isFitToContents = false
                    setExpandedOffset(expandOffset?.let { dpToPx(it.toFloat()).toInt() } ?: run {  resources.getDimensionPixelOffset(0)})
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    fun Intent?.launchActivity() {
        if (this != null) {
            (activity as? BaseActivity<*>)?.startActivity(this)
        }
    }

    fun Intent?.launchActivityAndFinish() {
        if (this != null) {
            (activity as? BaseActivity<*>)?.startActivity(this)
            activity?.finish()
        }
    }

    fun Intent?.launchActivityWithClearTask() {
        if (this != null) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            (activity as? BaseActivity<*>)?.startActivity(this)
        }
    }

    fun Intent.launchActivityForResult(code: Int) {
        (activity as? BaseActivity<*>)?.startActivityForResult(this, code)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigator) {
            mNavigator = context
        }
    }

    fun View.hideSoftKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    inner class BundleExtra<T>(private val key: String) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return arguments?.get(key) as? T
        }
    }

    inner class IntentExtra<T>(
        private val key: String,
        private val defaultValue: T? = null
    ) {
        private var backingField: T? = null

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return if (backingField == null) {
                backingField = (arguments?.get(key) as? T?) ?: defaultValue
                backingField
            } else {
                backingField
            }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            backingField = value
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun setWhiteNavigationBar(dialog: Dialog) {
        val window: Window? = dialog.window
        if (window != null) {
            val metrics = DisplayMetrics()
            window.windowManager.defaultDisplay.getMetrics(metrics)
            val dimDrawable = GradientDrawable()
            val navigationBarDrawable = GradientDrawable()
            navigationBarDrawable.shape = GradientDrawable.RECTANGLE
            navigationBarDrawable.setColor(Color.WHITE)
            val layers =
                arrayOf<Drawable>(dimDrawable, navigationBarDrawable)
            val windowBackground = LayerDrawable(layers)
            windowBackground.setLayerInsetTop(1, metrics.heightPixels)
            window.setBackgroundDrawable(windowBackground)
        }
    }

    //region navigation

    override fun navigateTo(navDirections: NavDirections) {
        mNavigator?.navigateTo(navDirections)
    }
    override fun navigateTo(uri: Uri) {
        mNavigator?.navigateTo(uri)
    }

    override fun popBackStackToRoot() {
        mNavigator?.popBackStackToRoot()
    }

    override fun popBackStack(destinationId: Int, inclusive: Boolean) {
        mNavigator?.popBackStack(destinationId, inclusive)
    }

    override fun popBackStack() {
        mNavigator?.popBackStack()
    }
    //endregion
}