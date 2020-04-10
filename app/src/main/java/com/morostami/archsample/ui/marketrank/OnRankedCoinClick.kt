/*
 * *
 *  * Created by Moslem Rostami on 4/8/20 12:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 12:06 PM
 *
 */

package com.morostami.archsample.ui.marketrank

import com.morostami.archsample.domain.model.RankedCoin

interface OnRankedCoinClick {
    fun onItemClicked(rankedCoin: RankedCoin, position: Int)
    fun onFavoriteClicked(rankedCoin: RankedCoin, position: Int, setFavorite: Boolean)
}