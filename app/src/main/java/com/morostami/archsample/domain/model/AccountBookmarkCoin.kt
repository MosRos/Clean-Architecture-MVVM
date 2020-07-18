/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 8:20 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 8:20 PM
 *
 */

package com.morostami.archsample.domain.model

import androidx.room.*


data class AccountBookmarkCoin(
    @Embedded var account: Account,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "accountBook_id"
    )
    var bookmarkedCoins: List<RankedCoin>
)