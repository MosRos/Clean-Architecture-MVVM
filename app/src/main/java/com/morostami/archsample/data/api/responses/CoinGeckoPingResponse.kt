/*
 * *
 *  * Created by Moslem Rostami on 3/19/20 12:52 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/19/20 12:52 PM
 *
 */

package com.morostami.archsample.data.api.responses


import com.google.gson.annotations.SerializedName

data class CoinGeckoPingResponse(
    @SerializedName("gecko_says")
    var geckoSays: String? = null // (V3) To the Moon!
)