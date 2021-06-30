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
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.R
import com.morostami.archsample.data.local.converters.toRankedCoin
import com.morostami.archsample.databinding.FragmentMarketRankBinding
import com.morostami.archsample.model.RankedCoin
import com.morostami.archsample.ui.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MarketRanksFragment : Fragment(), OnRankedCoinClick {
    private val TAG : String = this.javaClass.simpleName

    @Inject
    lateinit var marketViewModel: MarketViewModel
    private lateinit var mContext: Context
    private lateinit var dataBinding: FragmentMarketRankBinding
    private lateinit var rankRecycler: RecyclerView
    private lateinit var marketRanksAdapter: MarketRanksAdapter

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
//        marketViewModel.getPagedRankedCoins()
    }

    private fun setObservers() {
        Timber.e("Start Observing values")
        viewLifecycleOwner.lifecycleScope.launch {
            marketViewModel.getPagedRankedCoins().collect{
                it?.let {
                    Timber.e("Collected Size is: ${it.toString()}")
                    updateRanksAdapter(it.mapSync { rankedCoinEntity -> rankedCoinEntity.toRankedCoin() })
                }
            }
        }
    }

    private fun initWidgets() {
        val llManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rankRecycler = dataBinding.rankRecycler
        rankRecycler.layoutManager = llManager

        marketRanksAdapter = MarketRanksAdapter(this)
        rankRecycler.adapter = marketRanksAdapter
    }

    private fun updateRanksAdapter(coins: PagingData<RankedCoin>) {
        viewLifecycleOwner.lifecycleScope.launch {
            marketRanksAdapter.submitData(coins)
        }
        dataBinding.progressBar.visibility = View.GONE
    }

    override fun onItemClicked(rankedCoin: RankedCoin, position: Int) {
        Toast.makeText(mContext, "${rankedCoin.symbol} + $position  clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onFavoriteClicked(rankedCoin: RankedCoin, position: Int, setFavorite: Boolean) {

    }

}