package com.jmlb0003.bbcnews.presentation.news.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jmlb0003.bbcnews.databinding.ItemListNewsBinding
import com.jmlb0003.bbcnews.domain.model.NewsItem

class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding: ItemListNewsBinding? = DataBindingUtil.bind(view)

    fun bindNews(news: NewsItem) {
        binding?.article = news
        itemView.tag = news
    }

}
