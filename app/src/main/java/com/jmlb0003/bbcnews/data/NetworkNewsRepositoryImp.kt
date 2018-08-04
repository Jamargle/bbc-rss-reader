package com.jmlb0003.bbcnews.data

import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import java.io.IOException

class NetworkNewsRepositoryImp(private val apiService: BBCNewsApiClient) : NetworkNewsRepository {

    override fun obtainNews(): List<NewsItem> {
        try {
            apiService.getFeed().execute().body()?.let { newsFeed ->
                return newsFeed.channel.items
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return emptyList()
    }

}
