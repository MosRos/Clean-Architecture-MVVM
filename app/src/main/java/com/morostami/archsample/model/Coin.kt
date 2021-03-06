package com.morostami.archsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Coin")
data class Coin(
    @SerializedName("id")
    var id: String? = "xxx",
    @SerializedName("name")
    var name: String? = "xxx", // 01coin
    @SerializedName("symbol")
    @PrimaryKey
    var symbol: String = "xxx" // zoc
)