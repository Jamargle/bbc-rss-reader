package com.jmlb0003.bbcnews.domain.repository

import com.jmlb0003.bbcnews.domain.model.NewsItem

interface NetworkNewsRepository {

    fun obtainNews(): List<NewsItem>

}
