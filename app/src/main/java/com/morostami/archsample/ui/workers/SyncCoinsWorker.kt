/*
 * *
 *  * Created by Moslem Rostami on 7/6/20 6:46 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/6/20 6:46 PM
 *
 */

package com.morostami.archsample.ui.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.haroldadmin.cnradapter.NetworkResponse
import com.morostami.archsample.MainApp
import com.morostami.archsample.data.api.CoinGeckoService
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.CoinsRoomDao
import com.morostami.archsample.domain.model.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class SyncCoinsWorker constructor(
    val context: Context,
    val workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {

    @Inject
    lateinit var coinsRoomDao: CoinsRoomDao
    @Inject
    lateinit var coinGeckoService: CoinGeckoService

    companion object {
        private const val TAG = "SyncCoinsWorker"
    }

    override suspend fun doWork(): Result {
        (context.applicationContext as MainApp).appComponent.injectSyncWorker(this)
        try {
            val netWorkResp: NetworkResponse<List<Coin>, CoinGeckoApiError> = withContext(Dispatchers.IO) { fetchFromApi() }
            return when(netWorkResp) {
                is NetworkResponse.Success -> {
                    netWorkResp.body?.let { coins ->
                        saveCoins(coins)
                    }
                    Result.success()
                }
                else -> {
                    Result.failure()
                }
            }
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> {
        val coinsResult = coinGeckoService.getCoins()
        when(coinsResult){
            is NetworkResponse.Success -> Timber.e(coinsResult.body.size.toString())
            is NetworkResponse.NetworkError -> Timber.e(coinsResult.error.toString())
            is NetworkResponse.ServerError -> Timber.e(coinsResult.body.toString())
        }
        return coinsResult
    }

    suspend fun saveCoins(coins: List<Coin>) {
        Timber.e("Coins To Insert In DB ${coins.size}")
        withContext(Dispatchers.IO){
            coinsRoomDao.insertCoins(coins)
        }
    }
}