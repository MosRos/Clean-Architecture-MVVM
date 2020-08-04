/*
 * *
 *  * Created by Moslem Rostami on 7/25/20 2:33 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/25/20 2:33 PM
 *
 */

package com.morostami.archsample.domain.model

import com.morostami.archsample.data.api.responses.Links

data class CoinInfo(
    var id: String? = null, // bitcoin
    var symbol: String? = null, // btc
    var name: String? = null, // Bitcoin
    var marketCapRank: Int? = null, // 1
    var liquidityScore: Double? = null, // 100.052
    var imageUrl: String? = null,
    var description: String? = null,
    var hashingAlgorithm: String? = null, // SHA-256
    var countryOrigin: String? = null,
    var lastUpdated: String? = null, // 2020-07-25T08:08:09.170Z
    var sentimentVotesDownPercentage: Double? = null, // 27.8
    var sentimentVotesUpPercentage: Double? = null, // 72.2
    var links: Links? = null
)