/*
 * *
 *  * Created by Moslem Rostami on 7/10/20 1:54 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/10/20 1:54 AM
 *
 */

package com.morostami.archsample.ui.search

import com.morostami.archsample.model.Coin

interface OnCoinClick {
    fun onItemClicked(coin: Coin)
}