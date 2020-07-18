/*
 * *
 *  * Created by Moslem Rostami on 7/15/20 9:17 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/15/20 9:17 PM
 *
 */

package com.morostami.archsample.domain.model

import androidx.room.*

@Entity
data class Account(
    @PrimaryKey
    @ColumnInfo(name = "account_id")
    val accountId: String
)
