/*
 * *
 *  * Created by Moslem Rostami on 4/8/20 12:19 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 4/8/20 11:46 AM
 *
 */

package com.morostami.archsample.ui.bindadapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load
import coil.request.CachePolicy
import coil.request.Request
import coil.transform.CircleCropTransformation
import com.morostami.archsample.R
import com.morostami.archsample.utils.LoadingState

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter(value = ["setImageUrl", "placeholderImage"])
fun ImageView.bindImageUrl(url: String?,@DrawableRes placeholderImage: Int?) {
    if (url != null && url.isNotBlank()) {
        this.load(url){
            networkCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
            placeholder(R.drawable.placeholder_character)
            placeholderImage?.let {
                placeholder(it)
            }
            transformations(CircleCropTransformation())
        }
    }
}

@BindingAdapter("setImageUrl")
fun AppCompatImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        this.load(url){
            networkCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
            placeholder(R.drawable.placeholder_character)
            transformations(CircleCropTransformation())
        }
    }
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}


@BindingAdapter(value = ["setupVisibility"])
fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
    loadingState?.let {
        this.visibility = when(it.status) {
            LoadingState.Status.LOADING -> View.VISIBLE
            LoadingState.Status.SUCCESS -> View.GONE
            LoadingState.Status.FAILED -> View.INVISIBLE
        }
    }
}