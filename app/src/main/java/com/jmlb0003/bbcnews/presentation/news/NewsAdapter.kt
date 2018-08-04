package com.jmlb0003.bbcnews.presentation.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.inflate

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private val dataSet = mutableListOf<String>()

    fun bindNews(news: List<String>) {
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
