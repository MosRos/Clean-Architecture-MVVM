/*
 * *
 *  * Created by Moslem Rostami on 3/25/20 7:10 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/25/20 7:10 PM
 *
 */

package com.morostami.archsample.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.MainApp
import com.morostami.archsample.R
import com.morostami.archsample.databinding.FragmentCoinsListBinding
import com.morostami.archsample.di.CoinsComponent
import com.morostami.archsample.domain.model.Coin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CoinListFragment : Fragment() {
    private val TAG : String = this.javaClass.simpleName

    @Inject
    lateinit var mainViewModel: MainViewModel
    lateinit var mContext: Context
    lateinit var dataBinding: FragmentCoinsListBinding
    lateinit var coinsRecycler: RecyclerView
    lateinit var coinsAdapter: CoinsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).coinsComponent.injectCoinsListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coins_list, container, false)
        mContext = context ?: dataBinding.rootLayout.context
        dataBinding.mainViewModel = mainViewModel
        dataBinding.lifecycleOwner = this
        return dataBinding.rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        setObservers()
    }

    private fun setObservers() {
        mainViewModel.fragName.set("Coins List")
        mainViewModel.coinsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                updateCoinsAdapter(it)
            }
        })
    }

    private fun initWidgets() {
        val noOfCulumns = resources.getInteger(R.integer.coins_grid_columns)
        val gridLM = GridLayoutManager(mContext, noOfCulumns, RecyclerView.VERTICAL, false)
        coinsRecycler = dataBinding.coinsRecycler
        coinsRecycler.layoutManager = gridLM

        coinsAdapter = CoinsAdapter()
        dataBinding.coinsdAdapter = coinsAdapter
    }

    private fun updateCoinsAdapter(coins: List<Coin>) {
        coinsAdapter.setCoinsList(coins)
    }
}