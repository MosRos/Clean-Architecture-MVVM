/*
 * *
 *  * Created by Moslem Rostami on 8/5/20 1:12 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/5/20 1:12 AM
 *
 */

package com.morostami.archsample.data.api.responses


import com.google.gson.annotations.SerializedName

data class Roi(
 @SerializedName("currency")
 var currency: String? = null, // btc
 @SerializedName("percentage")
 var percentage: Double? = null, // 4524.891110573113
 @SerializedName("times")
 var times: Double? = null // 45.248911105731125
)