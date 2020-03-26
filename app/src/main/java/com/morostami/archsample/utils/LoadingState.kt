/*
 * *
 *  * Created by Moslem Rostami on 3/26/20 1:25 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 1:25 AM
 *
 */

package com.morostami.archsample.utils

@Suppress("DataClassPrivateConstructor")
data class LoadingState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.LOADING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }
}