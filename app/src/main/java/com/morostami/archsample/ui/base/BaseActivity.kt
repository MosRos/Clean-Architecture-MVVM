package com.roundtableapps.cloudrake.common.base

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.morostami.archsample.R
import com.morostami.archsample.ui.base.Navigator
import kotlin.reflect.KProperty


abstract class BaseActivity<V : BaseViewModel>(
    private val hasDefaultTransition: Boolean = true,
    private val hasFadeTransition: Boolean = false,
    private val needConfigWindowStyle: Boolean = true
) : AppCompatActivity(), Navigator {

    abstract val viewModel: V
    abstract val binding : ViewBinding
    private lateinit var navigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(getThemeMode())
        super.onCreate(savedInstanceState)
        setContentView(requireNotNull(binding).root)
        navigator = ActivityNavigator(this)

        if (needConfigWindowStyle)
            configWindowStyle()

        configTransition()
        setupViewModels()
        if (handleWebSocket()) {
//            viewModel.openWebSocket()
            observeWebSocketConnectionStatus()
        }
    }

    override fun onPause() {
        super.onPause()

        try {
            currentFocus?.hideSoftKeyboard()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (handleWebSocket())
//            viewModel.closeWebSocket()
    }

    private fun setupViewModels() {
//        App.instance.tokenExpireLiveData.observe(this, Observer {
//            if (it.isTokenExpired) {
//                onTokenExpired()
//            }
//        })
//        App.instance.showForceUpdateLiveData.observe(this, Observer {
//            showForceUpdateDialog(it)
//        })

        viewModel.onThemeStateChanged().observe(this, Observer {
            Log.e("BaseActivity", "BaseActivity: $it")
            this.recreate()
        })
    }

    fun getThemeMode() : Int {
        return viewModel.themeMode
    }

    open fun onTokenExpired() {
        expLogout()
//        OneSignal.deleteTag(NotificationData.ONE_SIGNAL_TAG.value)
    }

    private fun expLogout() {
//        closeAllNotifications()
//        if (isTokenizedActivity()) {
//            navigate(SplashActivity.intent(this))
//            finishAffinity()
//        }
    }

//    private fun isTokenizedActivity(): Boolean =
//        this !is SplashActivity
//                && this !is IntroActivity

    protected fun setScreenConfigTurnOn(turnON: Boolean = true) {
        if (Build.VERSION.SDK_INT < 27) {
            val win = window
            win.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
            win.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
            )
        } else {
            setShowWhenLocked(turnON)
            setTurnScreenOn(turnON)
        }
    }

    private fun configTransition() {
        if (hasDefaultTransition)
            setupDefaultTransition()
        else if (hasFadeTransition)
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
    }

    open fun setupDefaultTransition(isBack: Boolean = false) {
        if (isBack)
            overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_pop_enter_anim)
        else
            overridePendingTransition(R.anim.nav_default_exit_anim, R.anim.nav_default_pop_exit_anim)
    }

    inner class IntentExtra<T>(
        private val key: String,
        private val defaultValue: T? = null
    ) {
        private var backingField: T? = null

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return if (backingField == null) {
                backingField = (intent?.extras?.get(key) as? T?) ?: defaultValue
                backingField
            } else {
                backingField
            }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            backingField = value
        }
    }

    inner class IntentExtraNotNull<T>(
        private val key: String,
        private val defaultValue: T
    ) {
        private var backingField: T? = null

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return if (backingField == null) {
                backingField = (intent?.extras?.get(key) as? T?) ?: defaultValue
                backingField!!
            } else {
                backingField!!
            }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            backingField = value
        }
    }

    private fun configWindowStyle() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val toolbar = (this
//                .findViewById<View>(android.R.id.content) as? ViewGroup)?.getChildAt(0) as? ViewGroup
//            toolbar?.setOnApplyWindowInsetsListener { v, insets ->
//                toolbar.setPadding(0, 0, 0, insets.systemWindowInsetBottom) // status bar height
//                insets
//            }
//        }
    }

    protected fun setStatusBarColor(color: Int) {
// clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//// finally change the color
//        window.statusBarColor = ContextCompat.getColor(this, color)
    }


    fun View.hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }


    @IdRes
    abstract fun navHostId(): Int?

    fun navigate(
        intent: Intent,
        options: NavOptions? = null,
        navExtras: ActivityNavigator.Extras? = null
    ) {

        val navOptions = options ?: NavOptions.Builder().setLaunchSingleTop(true).build()

        navigator
            .navigate(
                navigator.createDestination().setIntent(intent), null, navOptions, navExtras
            )
    }

    override fun navigateTo(navDirections: NavDirections) {
        try {
            navHostId()?.let { findNavController(it).navigate(navDirections) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun navigateTo(uri: Uri) {
        navHostId()?.let { findNavController(it).navigate(uri) }
    }

    override fun popBackStackToRoot() {
        navHostId()?.let {
            val destinationId = findNavController(it).graph.startDestination
            popBackStack(destinationId, false)
        }
    }

    override fun popBackStack(destinationId: Int, inclusive: Boolean) {
        navHostId()?.let { findNavController(it).popBackStack(destinationId, inclusive) }
    }

    override fun popBackStack() {
        navHostId()?.let { findNavController(it).popBackStack() }
    }

    private fun observeWebSocketConnectionStatus() {
//        viewModel.getWebSocketConnectionStatus().observe(this, {
//            when (it) {
//                SocketConnection.CLOSED,
//                SocketConnection.CLOSING,
//                SocketConnection.WAITING_FOR_NETWORK,
//                SocketConnection.FAILED -> {
//                    onSocketConnectionFailed()
//                }
//                SocketConnection.OPENING -> {
//                }
//                SocketConnection.OPEN -> {
//                    onSocketConnectionOpen()
//                }
//                else -> {
//                }
//            }
//        })
    }

    open fun handleWebSocket(): Boolean = false

    open fun onSocketConnectionOpen() {}

    open fun onSocketConnectionFailed() {}

    private fun showForceUpdateDialog(forceUpdateError: String) {

//        if (forceUpdateError.showForceUpdateDialog) {
//            val errDialog = AlertDialog
////        ContextThemeWrapper(this, R.style.----)
//                .Builder(this)
//                .setTitle(R.string.error)
//                .setMessage(forceUpdateError.errorMessage)
//                .setPositiveButton(getString(R.string.ok)) { _, _ ->
//                    //Redirect to market
//                    updateApp()
//                    finish()
//                }
//                .setNegativeButton(getString(R.string.cancel)) { _, _ ->
//                    finishAffinity()
//                }
//                .setCancelable(false)
//            errDialog.show()
//        }
    }

    private fun updateApp() {
        val appPackageName = packageName
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (error: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }

    }
}
