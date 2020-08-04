/*
 * *
 *  * Created by Moslem Rostami on 8/2/20 11:29 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 11:29 AM
 *
 */

package com.morostami.archsample.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Coin")
data class CoinEntity (
    @SerializedName("id")
    var id: String? = "xxx",
    @SerializedName("name")
    var name: String? = "xxx", // 01coin
    @SerializedName("symbol")
    @PrimaryKey
    var symbol: String = "xxx" // zoc
)