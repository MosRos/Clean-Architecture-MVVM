package com.morostami.archsample

import android.app.Application
import android.util.Log
import com.morostami.archsample.di.AppComponent
import com.morostami.archsample.di.DaggerAppComponent
import org.jetbrains.annotations.NonNls
import timber.log.Timber


class MainApp : Application() {

    companion object {
        private lateinit var instance: MainApp

        fun getInstance() : MainApp {
            return instance
        }
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(instance)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    fun getAppContext() : Application {
        return instance
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