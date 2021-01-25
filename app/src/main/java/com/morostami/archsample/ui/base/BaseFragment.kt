package com.roundtableapps.cloudrake.common.base

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.morostami.archsample.ui.base.Navigator
import kotlin.reflect.KProperty

abstract class BaseFragment<V : BaseViewModel>(
    private var isBottomNavigationVisible: Boolean = false
) : Fragment(), IOnBackPressed, Navigator {

    abstract val viewModel: V
    abstract val viewId : Int
    open val title = "Base"

    private var mNavigator: Navigator? = null
//    private var mHomeController: HomeController? = null

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

    override fun onResume() {
        super.onResume()
        if (parentFragment !is NavHostFragment) return
        handleBottomNavigationVisibility()
    }

    fun setBottomNavigationVisibility(isBottomNavigationVisible: Boolean) {
        this.isBottomNavigationVisible = isBottomNavigationVisible
        view?.post {
            handleBottomNavigationVisibility()
            view?.requestLayout()
        }
    }

    private fun handleBottomNavigationVisibility() {
        if(isBottomNavigationVisible) showBottomNavigation() else hideBottomNavigation()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigator) {
            mNavigator = context
        }
//        if (context is HomeController) {
//            mHomeController = context
//        }
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

    override fun onBackPressed(): Boolean {
        for (f in childFragmentManager.fragments) {
            if (f.isVisible) {
                return f is IOnBackPressed && (f as IOnBackPressed).onBackPressed()
            }
        }
        return false
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


    fun closeAllNotifications() {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.cancelAll()
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

    private fun hideBottomNavigation() {
//        mHomeController?.hideBottomNavigation()
    }

    private fun showBottomNavigation() {
//        mHomeController?.showBottomNavigation()
    }

    //endregion
}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}