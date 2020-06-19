/*
 * *
 *  * Created by Moslem Rostami on 6/18/20 9:33 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/18/20 9:33 PM
 *
 */

package com.morostami.archsample.ui.marketrank

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.databinding.ListItemRankedCoinBinding
import com.morostami.archsample.domain.model.RankedCoin

class RankedCoinViewHolder(val databinding: ListItemRankedCoinBinding) : RecyclerView.ViewHolder(databinding.rootLayout) {
    val rootLayout: View = databinding.rootLayout

    fun bind(rankedCoin: RankedCoin, position: Int, onRankedCoinClick: OnRankedCoinClick) {
        rootLayout.setOnClickListener {
            onRankedCoinClick.onItemClicked(rankedCoin, position)
        }
    }
}