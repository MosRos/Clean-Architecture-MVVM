/*
 * *
 *  * Created by Moslem Rostami on 4/14/20 9:15 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 11:35 PM
 *
 */

package com.morostami.archsample.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.databinding.ListItemCoinBinding
import com.morostami.archsample.domain.model.Coin

class CoinsAdapter(private val onCoinClick: (Coin) -> Unit) :
    PagingDataAdapter<Coin, CoinsAdapter.CoinsViewHolder>(COINS_COMPARATOR) {

    companion object {
        private val COINS_COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding = ListItemCoinBinding.inflate(LayoutInflater.from(parent.context))
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin: Coin? = getItem(position)
        coin?.let {
            holder.databinding.coinItem = coin
            holder.bind(coin, onCoinClick)
            holder.databinding.executePendingBindings()
        }
    }

    class CoinsViewHolder(val databinding: ListItemCoinBinding) :
        RecyclerView.ViewHolder(databinding.rootLayout) {
        fun bind(coin: Coin, onCoinClick: (Coin) -> Unit) {
            databinding.rootLayout.setOnClickListener {
                onCoinClick.invoke(coin)
            }
        }
    }
}