package com.jmlb0003.bbcnews.presentation.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.utils.inflate

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private val dataSet = mutableListOf<NewsItem>()

    fun bindNews(news: List<NewsItem>) {
        dataSet.clear()
        dataSet.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(parent.inflate(R.layout.item_list_news))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindNews(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
