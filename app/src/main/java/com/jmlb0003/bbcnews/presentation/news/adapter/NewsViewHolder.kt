package com.jmlb0003.bbcnews.presentation.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmlb0003.bbcnews.domain.model.NewsItem
import kotlinx.android.synthetic.main.item_list_news.view.*

class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindNews(news: NewsItem) {
        itemView.tag = news
        view.news_title.text = news.title
    }

}
