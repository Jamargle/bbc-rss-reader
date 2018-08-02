package com.jmlb0003.bbcnews.presentation.news

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_list_news.view.*

class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindNews(news: String) {
        view.news_title.text = news
    }

}
