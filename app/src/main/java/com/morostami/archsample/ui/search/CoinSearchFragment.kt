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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morostami.archsample.R
import com.morostami.archsample.databinding.FragmentCoinsSearchBinding
import com.morostami.archsample.domain.model.Coin
import com.morostami.archsample.ui.MainActivity
import com.morostami.archsample.ui.MainViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class CoinSearchFragment : Fragment() {
    private val TAG : String = this.javaClass.simpleName

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var mContext: Context
    private lateinit var dataBinding: FragmentCoinsSearchBinding
    private lateinit var coinsRecycler: RecyclerView
    private lateinit var coinsAdapter: CoinsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).coinsComponent.injectCoinsListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coins_search, container, false)
        mContext = context ?: dataBinding.rootLayout.context
        dataBinding.mainViewModel = mainViewModel
        dataBinding.lifecycleOwner = this
        return dataBinding.rootLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        setObservers()
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        setObservers()
        mainViewModel.searchCoins("")
    }

    private fun setObservers() {
        mainViewModel.fragName.set("Search Coins")
        mainViewModel.coinSearchResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                updateCoinsAdapter(it)
            }
        })
    }

    private fun initWidgets() {
        val noOfColumns = resources.getInteger(R.integer.coins_grid_columns)
        val gridLM = GridLayoutManager(mContext, noOfColumns, RecyclerView.VERTICAL, false)
        coinsRecycler = dataBinding.coinsRecycler
        coinsRecycler.layoutManager = gridLM

        coinsAdapter = CoinsAdapter()
        dataBinding.coinsdAdapter = coinsAdapter
    }

    private fun setListeners() {
        dataBinding.searchEdt.addTextChangedListener(object : TextWatcher{

            private val DELAY: Long = 750
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
        mainViewModel.searchCoins(input)
        setObservers()
    }

    private fun updateCoinsAdapter(coins: List<Coin>) {
        coinsAdapter.setCoinsList(coins)
    }
}