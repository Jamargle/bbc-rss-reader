package com.jmlb0003.bbcnews.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmlb0003.bbcnews.presentation.news.adapter.NewsAdapter

@BindingAdapter("news")
internal fun setNews(recyclerView: RecyclerView, newsList: List<String>?) {
    newsList?.let {
        (recyclerView.adapter as? NewsAdapter)?.bindNews(it)
    }
}

@BindingAdapter("visible")
internal fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}