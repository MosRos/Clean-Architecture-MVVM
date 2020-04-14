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
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.databinding.ListItemCoinBinding
import com.morostami.archsample.domain.model.Coin

class CoinsAdapter() : RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder>() {

    private var coinsList: List<Coin> = ArrayList()

    fun setCoinsList(coins: List<Coin>) {
        this.coinsList = coins
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding = ListItemCoinBinding.inflate(LayoutInflater.from(parent.context))
        return CoinsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin: Coin = coinsList[position]
        holder.databinding.coinItem = coin
        holder.databinding.executePendingBindings()
    }

    inner class CoinsViewHolder(val databinding: ListItemCoinBinding) : RecyclerView.ViewHolder(databinding.rootLayout) {

    }

}