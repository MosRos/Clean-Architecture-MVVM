/*
 * *
 *  * Created by Moslem Rostami on 8/2/20 11:32 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 11:32 AM
 *
 */

package com.morostami.archsample.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GlobalInfo")
data class GlobalInfoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var activeCryptocurrencies: Int = 0,
    var markets: Int = 0,
    var endedIcos: Int = 0,
    var ongoingIcos: Int = 0,
    var upcomingIcos: Int = 0,
    var marketCapChangePercentage24hUsd: Double = 0.0,
    var marketCapPercentage: Map<String, Double>? = null,
    var totalMarketCapUsd: Double = 0.0,
    var totalVolumeUsd: Double = 0.0,
    var updatedAt: Long = 0
)