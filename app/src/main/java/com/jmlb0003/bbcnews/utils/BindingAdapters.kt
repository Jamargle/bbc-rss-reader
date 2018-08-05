package com.jmlb0003.bbcnews.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.presentation.news.adapter.NewsAdapter
import com.squareup.picasso.Picasso
import java.util.*

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
internal fun setImageResource(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Picasso.with(imageView.context)
                .load(imageUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_news)
                .into(imageView)
    }
}

@BindingAdapter("formattedDate")
internal fun setFormattedDate(view: TextView, date: Date?) {
    date?.let {
        view.text = when (date.getTimeAgo()) {
            DatePeriod.JustNow -> view.context.resources.getString(R.string.right_now)
            is DatePeriod.MinutesAgo -> {
                val timeGap = (date.getTimeAgo() as DatePeriod.MinutesAgo).timeGap
                view.context.resources.getQuantityString(R.plurals.minutes_ago, timeGap, timeGap)
            }
            is DatePeriod.HoursAgo -> {
                val timeGap = (date.getTimeAgo() as DatePeriod.HoursAgo).timeGap
                view.context.resources.getQuantityString(R.plurals.hours_ago, timeGap, timeGap)
            }
            is DatePeriod.FullTimeDate -> (date.getTimeAgo() as DatePeriod.FullTimeDate).dateToShow
        }
    }
}
