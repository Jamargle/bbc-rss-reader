package com.jmlb0003.bbcnews.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.jmlb0003.bbcnews.presentation.news.adapter.NewsAdapter

@BindingAdapter("news")
internal fun setNews(recyclerView: RecyclerView, newsList: List<String>?) {
    newsList?.let {
        (recyclerView.adapter as? NewsAdapter)?.bindNews(it)
    }
}
