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
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load
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
            crossfade(true)
            placeholderImage?.let {
                placeholder(it)
            }
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