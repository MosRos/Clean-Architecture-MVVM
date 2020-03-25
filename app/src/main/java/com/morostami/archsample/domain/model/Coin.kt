package com.morostami.archsample.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

@Entity
open class Coin(
    @SerializedName("id")
    var id: String? = "xxx",
    @SerializedName("name")
    var name: String? = "xxx", // 01coin
    @SerializedName("symbol")
    @PrimaryKey
    @io.realm.annotations.PrimaryKey var symbol: String = "xxx" // zoc
) : RealmObject()