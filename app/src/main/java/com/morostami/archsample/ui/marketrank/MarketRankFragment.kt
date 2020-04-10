/*
 * *
 *  * Created by Moslem Rostami on 4/8/20 12:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 12:15 PM
 *
 */

package com.morostami.archsample.ui.marketrank

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.R
import com.morostami.archsample.databinding.FragmentMarketRankBinding
import com.morostami.archsample.domain.model.RankedCoin
import com.morostami.archsample.ui.MainActivity
import timber.log.Timber
import javax.inject.Inject

class MarketRankFragment : Fragment(), OnRankedCoinClick {
    private val TAG : String = this.javaClass.simpleName

    @Inject
    lateinit var marketViewModel: MarketViewModel
    private lateinit var mContext: Context
    private lateinit var dataBinding: FragmentMarketRankBinding
    private lateinit var rankRecycler: RecyclerView
    private lateinit var ranksAdapter: RankedCoinAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).coinsComponent.injectMarketRankFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_rank, container, false)
        mContext = context ?: dataBinding.rootLayout.context
        dataBinding.marketViewModel = marketViewModel
        dataBinding.lifecycleOwner = this
        return dataBinding.rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        marketViewModel.getMarketRanks()
    }

    private fun setObservers() {
        Timber.e("Start Observing values")
        marketViewModel.marketRankedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                Timber.e("received ranks ${it.size}")
                updateRanksAdapter(it)
            }
        })
    }

    private fun initWidgets() {
        val llManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rankRecycler = dataBinding.rankRecycler
        rankRecycler.layoutManager = llManager

        ranksAdapter = RankedCoinAdapter(this)
        dataBinding.rankAdapter = ranksAdapter
    }

    private fun updateRanksAdapter(coins: List<RankedCoin>) {
        ranksAdapter.setCoinsList(coins.sortedBy { it.marketCapRank })
    }

    override fun onItemClicked(rankedCoin: RankedCoin, position: Int) {
        Toast.makeText(mContext, "${rankedCoin.symbol} + $position  clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onFavoriteClicked(rankedCoin: RankedCoin, position: Int, setFavorite: Boolean) {

    }

}