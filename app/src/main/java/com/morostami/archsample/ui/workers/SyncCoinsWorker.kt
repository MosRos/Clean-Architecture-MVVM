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
import com.morostami.archsample.data.api.RemoteDataSource
import com.morostami.archsample.data.api.responses.CoinGeckoApiError
import com.morostami.archsample.data.local.CoinsLocalDataSource
import com.morostami.archsample.data.local.converters.toEntity
import com.morostami.archsample.data.local.entities.CoinEntity
import com.morostami.archsample.domain.model.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class SyncCoinsWorker constructor(
    val context: Context,
    val workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {

    @Inject
    lateinit var coinsLocalDataSource: CoinsLocalDataSource
    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    companion object {
        private const val TAG = "SyncCoinsWorker"
    }

    override suspend fun doWork(): Result {
        (context.applicationContext as MainApp).appComponent.injectSyncWorker(this)
        try {
            val netWorkResp: NetworkResponse<List<Coin>, CoinGeckoApiError> = withContext(Dispatchers.IO) {
                fetchFromApi()
            }
            return when(netWorkResp) {
                is NetworkResponse.Success -> {
                    netWorkResp.body.also { coins ->
                        saveCoins(coins.map { coin -> coin.toEntity() })
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

    private suspend fun fetchFromApi() : NetworkResponse<List<Coin>, CoinGeckoApiError> = remoteDataSource.getCoins()

    private suspend fun saveCoins(coins: List<CoinEntity>) {
        Timber.e("Coins To Insert In DB ${coins.size}")
        withContext(Dispatchers.IO){
            coinsLocalDataSource.insertCoins(coins)
        }
    }
}