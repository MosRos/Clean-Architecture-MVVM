/*
 * *
 *  * Created by Moslem Rostami on 4/14/20 9:15 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/11/20 1:23 AM
 *
 */

package com.morostami.archsample.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.R
import com.morostami.archsample.data.local.converters.toCoin
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.databinding.FragmentCoinsSearchBinding
import com.morostami.archsample.model.Coin
import com.morostami.archsample.ui.MainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CoinSearchFragment : Fragment() {
    private val TAG: String = this.javaClass.simpleName

    @Inject
    lateinit var searchViewModel: SearchViewModel
    private lateinit var mContext: Context
    private lateinit var dataBinding: FragmentCoinsSearchBinding
    private lateinit var coinsRecycler: RecyclerView
    private lateinit var coinsAdapter: CoinsAdapter

    private val onCoinClicked: (Coin) -> Unit =  {coin->
        Toast.makeText(mContext, "${coin.name} + ${coin.symbol} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).coinsComponent.injectCoinsListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_coins_search, container, false)
        mContext = context ?: dataBinding.rootLayout.context
        dataBinding.viewModel = searchViewModel
        dataBinding.lifecycleOwner = this
        return dataBinding.rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            delay(200)
            searchViewModel.searchCoins("").collect { page ->
                updateCoinsAdapter(page)
            }
        }
    }

    private fun initWidgets() {
        val noOfColumns = resources.getInteger(R.integer.coins_grid_columns)
        val gridLM = GridLayoutManager(mContext, noOfColumns, RecyclerView.VERTICAL, false)
        coinsRecycler = dataBinding.coinsRecycler
        coinsRecycler.layoutManager = gridLM

        coinsAdapter = CoinsAdapter(onCoinClicked)
        coinsRecycler.adapter = coinsAdapter
    }

    private fun setListeners() {
        dataBinding.searchEdt.addTextChangedListener(object : TextWatcher {

            private val DELAY: Long = 650
            var searchJob = SupervisorJob()
            val searchScope = CoroutineScope(Dispatchers.Main + searchJob)

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchScope.coroutineContext.cancelChildren()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchScope.launch {
                    delay(DELAY)
                    doSearch(p0.toString().trim())
                }
            }
        })
    }

    private fun doSearch(input: String) {
        lifecycleScope.launch {
            searchViewModel.searchCoins(input).collect { pages ->
                updateCoinsAdapter(pages)
            }
        }
    }

    private suspend fun updateCoinsAdapter(coins: PagingData<CoinEntity>) {
        coinsAdapter.submitData(coins.mapSync { coinEntity -> coinEntity.toCoin() })
    }

//    override fun onItemClicked(coin: Coin) {
//        Toast.makeText(mContext, "${coin.name} + ${coin.symbol} clicked", Toast.LENGTH_SHORT).show()
//    }
}