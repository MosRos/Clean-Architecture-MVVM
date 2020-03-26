/*
 * *
 *  * Created by Moslem Rostami on 3/26/20 12:56 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/26/20 12:56 AM
 *
 */

package com.morostami.archsample.utils

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        this.load(url)
    }
}

@BindingAdapter(value = ["setupVisibility"])
fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
    loadingState?.let {
        isVisible = when(it.status) {
            LoadingState.Status.LOADING -> true
            LoadingState.Status.SUCCESS -> false
            LoadingState.Status.FAILED -> false
        }
    }
}