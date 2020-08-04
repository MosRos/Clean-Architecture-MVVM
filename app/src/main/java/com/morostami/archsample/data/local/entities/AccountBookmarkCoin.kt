/*
 * *
 *  * Created by Moslem Rostami on 8/2/20 11:28 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/16/20 12:32 PM
 *
 */

package com.morostami.archsample.data.local.entities

import androidx.room.*
import com.morostami.archsample.domain.model.RankedCoin


data class AccountBookmarkCoin(
    @Embedded var accountEntity: AccountEntity,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "accountBook_id"
    )
    var bookmarkedCoins: List<RankedCoinEntity>
)