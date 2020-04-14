/*
 * *
 *  * Created by Moslem Rostami on 3/26/20 2:59 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 2:59 AM
 *
 */

package com.morostami.archsample.di

import com.morostami.archsample.ui.search.CoinSearchFragment
import com.morostami.archsample.ui.MainActivity
import com.morostami.archsample.ui.marketrank.MarketRankFragment
import dagger.Subcomponent


@ActivityScope
@Subcomponent
interface CoinsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : CoinsComponent
    }

    fun injectMainActivity(mainActivity: MainActivity)
    fun injectCoinsListFragment(coinListFragment: CoinSearchFragment)
    fun injectMarketRankFragment(marketRankFragment: MarketRankFragment)
}