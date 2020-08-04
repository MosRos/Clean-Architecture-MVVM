/*
 * *
 *  * Created by Moslem Rostami on 8/2/20 11:42 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 11:38 AM
 *
 */

package com.morostami.archsample.data.local.entities

import androidx.room.*

@Entity(tableName = "Account")
data class AccountEntity(
    @PrimaryKey
    @ColumnInfo(name = "account_id")
    val accountId: String
)
