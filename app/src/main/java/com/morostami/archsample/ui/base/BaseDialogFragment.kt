package com.roundtableapps.cloudrake.common.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import com.morostami.archsample.R
import com.morostami.archsample.ui.base.Navigator
import com.morostami.archsample.utils.dpToPx
import kotlin.reflect.KProperty

abstract class BaseDialogFragment<V : BaseViewModel> : DialogFragment(), Navigator {
    abstract val viewModel: V
    abstract val viewId : Int

    var mNavigator: Navigator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewId, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { context ->
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog?.window?.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.background_dialog
                )
            )
        }
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)
        setupViewModels()
    }

    protected open fun setupViewModels() {

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

    override fun onResume() {
        super.onResume()
        context?.let {
            val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
            params.width = dpToPx(326f).toInt()
            params.height = dpToPx(326f).toInt()
            dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        }

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

    inner class BundleExtra<T>(private val key: String) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return arguments?.get(key) as? T
        }
    }
}