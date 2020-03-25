/*
 * *
 *  * Created by Moslem Rostami on 3/24/20 9:29 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/24/20 9:29 PM
 *
 */

package com.morostami.archsample.ui.utils

/**
 * A generic wrapper class around data request
 */

data class UiData<RequestedData>(
    var responseType: Status,
    var data: RequestedData? = null,
    var error: Exception? = null)

enum class Status { SUCCESSFUL, ERROR, LOADING }
