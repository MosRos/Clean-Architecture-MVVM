/*
 * *
 *  * Created by Moslem Rostami on 4/8/20 11:55 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 11:55 AM
 *
 */

package com.morostami.archsample.ui.marketrank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.databinding.ListItemRankedCoinBinding
import com.morostami.archsample.model.RankedCoin

class RankedCoinAdapter(private val onRankedCoinClick: OnRankedCoinClick) : RecyclerView.Adapter<RankedCoinViewHolder>() {
    private var coinsList: List<RankedCoin> = ArrayList()

    fun setCoinsList(coins: List<RankedCoin>) {
        this.coinsList = coins
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankedCoinViewHolder {
        val binding = ListItemRankedCoinBinding.inflate(LayoutInflater.from(parent.context))
        return RankedCoinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(holder: RankedCoinViewHolder, position: Int) {
        val coin: RankedCoin = coinsList[position]
        holder.databinding.rankedCoin = coin
        holder.bind(coin, position, onRankedCoinClick)
        holder.databinding.executePendingBindings()
    }
}