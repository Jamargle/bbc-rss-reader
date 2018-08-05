package com.jmlb0003.bbcnews.domain.repository

import com.jmlb0003.bbcnews.domain.model.NewsItem
import io.reactivex.Single

interface NetworkNewsRepository {

    fun obtainNews(): Single<List<NewsItem>>

}
