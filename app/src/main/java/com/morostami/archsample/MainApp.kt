package com.morostami.archsample

import android.app.Application
import android.util.Log
import com.morostami.archsample.di.AppComponent
import com.morostami.archsample.di.DaggerAppComponent
import io.realm.Realm
import org.jetbrains.annotations.NonNls
import timber.log.Timber


open class MainApp : Application() {

    private lateinit var _mainAppp: MainApp

    companion object {
        lateinit var instance: MainApp
            private set
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(_mainAppp)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        _mainAppp = this
        instance = this
        initRealm(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun initRealm(appContext: Application) {
        Realm.init(appContext)
    }

    fun getAppContext() : Application {
        return _mainAppp
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(
            priority: Int,
            tag: String?, @NonNls message: String,
            t: Throwable?
        ) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
//            FakeCrashLibrary.log(priority, tag, message)
//            if (t != null) {
//                if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t)
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t)
//                }
//            }
        }
    }
}