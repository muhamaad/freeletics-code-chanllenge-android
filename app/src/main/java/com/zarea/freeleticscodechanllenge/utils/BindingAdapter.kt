package com.zarea.freeleticscodechanllenge.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener

/**
 * Created by zarea at 2021
 */
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean?) {
    value?.let {
        view.visibility = if (it) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("onRefreshListener")
fun bindOnRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, listener: OnRefreshListener?) {
    swipeRefreshLayout.setOnRefreshListener(listener)
}

@BindingAdapter("refreshing")
fun bindRefreshing(swipeRefreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = refreshing
}
