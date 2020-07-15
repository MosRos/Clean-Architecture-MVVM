package com.morostami.archsample

import android.app.Application
import android.util.Log
import com.morostami.archsample.di.AppComponent
import com.morostami.archsample.di.DaggerAppComponent
import org.jetbrains.annotations.NonNls
import timber.log.Timber


class MainApp : Application() {

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

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
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
        }
    }
}