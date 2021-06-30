/*
 * *
 *  * Created by Moslem Rostami on 6/18/20 8:21 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/18/20 8:21 PM
 *
 */

package com.morostami.archsample.ui.marketrank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.morostami.archsample.databinding.ListItemRankedCoinBinding
import com.morostami.archsample.model.RankedCoin

class MarketRanksAdapter(private val onRankedCoinClick: OnRankedCoinClick) : PagingDataAdapter<RankedCoin, RankedCoinViewHolder>(COIN_COMPARATOR) {

    companion object {
        private val COIN_COMPARATOR = object : DiffUtil.ItemCallback<RankedCoin>() {
            override fun areItemsTheSame(oldItem: RankedCoin, newItem: RankedCoin): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(oldItem: RankedCoin, newItem: RankedCoin): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RankedCoinViewHolder, position: Int) {
        val coin: RankedCoin? = getItem(position)
        coin?.let {
            holder.databinding.rankedCoin = coin
            holder.bind(coin, position, onRankedCoinClick)
            holder.databinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankedCoinViewHolder {
        val binding = ListItemRankedCoinBinding.inflate(LayoutInflater.from(parent.context))
        return RankedCoinViewHolder(binding)
    }
}