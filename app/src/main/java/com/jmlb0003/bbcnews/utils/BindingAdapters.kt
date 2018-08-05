package com.jmlb0003.bbcnews.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.presentation.news.adapter.NewsAdapter

@BindingAdapter("news")
internal fun setNews(recyclerView: RecyclerView, newsList: List<NewsItem>?) {
    newsList?.let {
        (recyclerView.adapter as? NewsAdapter)?.bindNews(it)
    }
}

@BindingAdapter("article")
internal fun setNewsDetail(webView: WebView, newsLink: String?) {
    newsLink?.let {
        webView.loadUrl(it)
    }
}

@BindingAdapter("visible")
internal fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageSrc")
internal fun setImageResource(imageView: ImageView, imageUrl: String?) {}