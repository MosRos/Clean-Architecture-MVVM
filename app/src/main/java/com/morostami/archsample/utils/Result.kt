/*
 * *
 *  * Created by Moslem Rostami on 3/24/20 7:13 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 7:13 PM
 *
 */

package com.morostami.archsample.utils

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Failure(val exception: Exception) : Result<Nothing>()
}
